package com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping
import com.example.project_miniMart.datasource.local.preferences.DataStorePref
import com.example.project_miniMart.domain.repositories.HomeTask
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationVoucher
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent.CancelAlert
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent.CreateNewSale
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent.DeleteProductFromCar
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent.GetAllShoppingCar
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent.HideAlert
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent.ShowAlert
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.model.ShoppingCarState
import com.example.project_miniMart.utils.extensions.formatMoney
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.utils.uiManager.HomeEventManager
import com.example.project_miniMart.utils.uiManager.events.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCarViewModel @Inject constructor(
    private val homeTask: HomeTask,
    private val dataStorePref: DataStorePref,
) : ViewModel() {
    private val _state = MutableStateFlow(ShoppingCarState())
    val state: StateFlow<ShoppingCarState> = _state
    private lateinit var productToDelete: GroupShopping

    val channel = Channel<ShoppingCarIntent>(Channel.BUFFERED)

    init {
        channel.trySend(GetAllShoppingCar)
        setupIntents()
    }

    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        GetAllShoppingCar -> getAllShoppingCar()
                        is DeleteProductFromCar -> deleteProductCar(
                            it.productName,
                            it.category
                        )

                        HideAlert -> {
                            _state.value = _state.value.copy(showAlert = false)
                            channel.trySend(
                                DeleteProductFromCar(
                                    productToDelete.nameProducts,
                                    productToDelete.category
                                )
                            )
                        }

                        is ShowAlert -> {
                            productToDelete = it.productToDelete
                            _state.value = _state.value.copy(
                                showAlert = true,
                                errorMassageAlert = R.string.delete_item
                            )
                        }

                        CancelAlert -> _state.value =
                            _state.value.copy(showAlert = false)

                        CreateNewSale -> createNewSaleWithGroup()
                    }
                }
        }
    }

    private suspend fun createNewSaleWithGroup(){
        handleRequest(
            call = {homeTask.insertNewSale(
                group = _state.value.listShoppingCar,
                seller = _state.value.userName,
                total = _state.value.total
            )},
            onSuccess = {
                HomeEventManager.triggerEvent(HomeEvent.NavigateTo(DestinationVoucher(it)))
            },
            onError = {
                HomeEventManager.triggerEvent(HomeEvent.ShowSnackBar(it))
            }
        )
    }

    private suspend fun deleteProductCar(productName: String, category: String) {
        handleRequest(
            call = { homeTask.deleteProductShopping(productName, category) },
            onSuccess = {
                getAllShoppingCar()
            },
            onError = {
                HomeEventManager.triggerEvent(HomeEvent.ShowSnackBar(it))
            }
        )
    }

    private suspend fun getAllShoppingCar() {
        handleRequest(
            call = { homeTask.getAllShoppingCar() },
            onSuccess = {
                _state.value = _state.value.copy(
                    listShoppingCar = it,
                    total = it.sumOf { it.totalPrice }.formatMoney(),
                    userName = dataStorePref.getUserName.first()
                )
            },
            onError = {
                HomeEventManager.triggerEvent(HomeEvent.ShowSnackBar(it))
            }
        )
    }
}