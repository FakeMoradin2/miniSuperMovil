package com.example.project_miniMart.ui.flows.homeFlow.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity
import com.example.project_miniMart.datasource.local.preferences.DataStorePref
import com.example.project_miniMart.domain.models.ProductDomain
import com.example.project_miniMart.domain.repositories.HomeTask
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationAccount
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationHistory
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.intent.HomeIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.model.HomeStates
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.utils.uiManager.HomeEventManager
import com.example.project_miniMart.utils.uiManager.events.HomeEvent
import com.example.project_miniMart.utils.uiManager.events.HomeEvent.*
import com.example.project_miniMart.widgets.listCategories.ChipCategoryItem
import com.example.project_miniMart.widgets.loader.DsLoaderView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStorePref: DataStorePref,
    private val homeRepository: HomeTask,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeStates())
    val state: StateFlow<HomeStates> = _state

    private var completeListProducts = emptyList<ProductDomain>()


    val channel = Channel<HomeIntents>(Channel.BUFFERED)

    init {
        channel.trySend(HomeIntents.GetAllProducts)
        setupIntents()
    }


    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        HomeIntents.GetUserInfo -> _state.value = _state.value.copy(
                            userName = getUserName(),
                            userEmail = dataStorePref.getEmail.first()
                        )

                        HomeIntents.GetAllProducts -> getAllProducts()
                        is HomeIntents.CheckCategory -> checkNewCategory(it.label)
                        is HomeIntents.ShowAndHideButtonSheet -> _state.value =
                            _state.value.copy(showBottomSheet = it.show)

                        HomeIntents.HideAlert -> {
                            _state.value = _state.value.copy(
                                showAlert = false,
                                errorMassageAlert = 0,
                                typeAlert = null
                            )

                            if (_state.value.allCategories.isEmpty() || _state.value.allProducts.isEmpty()) HomeEventManager.triggerEvent(
                                HomeEvent.FinishApp
                            )
                        }

                        is HomeIntents.UpdateGridScrolling ->  _state.value =  _state.value.copy(isScrolling = it.isScrolling)
                        is HomeIntents.AddProductShoppingCar -> addProductShoppingCar(it.shoppingEntity)
                        HomeIntents.HideButtonSheet -> {
                            _state.value = _state.value.copy(showBottomSheet = false)
                            HomeEventManager.triggerEvent(NavigateTo(DestinationHistory))
                        }

                        HomeIntents.HideButtonSheetAccount -> {
                            _state.value = _state.value.copy(showBottomSheetAccount = false)
                            HomeEventManager.triggerEvent(NavigateTo(DestinationAccount))
                        }
                    }
                }
        }
    }

    private suspend fun addProductShoppingCar(shoppingEntity: ShoppingEntity){
        handleRequest(
            call = {homeRepository.insertShoppingCar(shoppingEntity)},
            onSuccess = {
                HomeEventManager.triggerEvent(HomeEvent.ShowSnackBar(it))
            },
            onError = {
                HomeEventManager.triggerEvent(HomeEvent.ShowSnackBar(it))
            }
        )
    }


    private fun checkNewCategory(label: String) {
        _state.value = _state.value.copy(
            allCategories = _state.value.allCategories.map {
                it.copy(isCheck = it.label == label)
            },
            allProducts = if (label == "All") completeListProducts else completeListProducts.filter { it.category == label }

        )
    }

    private suspend fun getAllProducts() {
        DsLoaderView.showLoader()
        handleRequest(
            call = { homeRepository.fetchAllProducts() },
            onSuccess = {
                completeListProducts = it

                val manualCategory = listOf(
                    ChipCategoryItem(label = "All", isCheck = true)
                )

                val categories = it
                    .map { product -> product.category }
                    .distinct()
                    .map { category -> ChipCategoryItem(label = category) }


                _state.value =
                    _state.value.copy(allProducts = it, allCategories = manualCategory + categories)
            },
            onError = {
                _state.value = _state.value.copy(
                    showAlert = true,
                    errorMassageAlert = it,
                    typeAlert = TypeAlert.ERROR
                )
            }
        )
    }

    private suspend fun getUserName(): String {
        val userName = dataStorePref.getUserName.first()
        val nameSplit = userName.split(" ").filter { it.isNotBlank() }
        val first = nameSplit.firstOrNull() ?: ""
        val last = nameSplit.lastOrNull() ?: ""
        return "$first $last!"
    }
}