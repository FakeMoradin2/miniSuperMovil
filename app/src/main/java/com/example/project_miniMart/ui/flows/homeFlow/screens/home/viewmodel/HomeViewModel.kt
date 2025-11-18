package com.example.project_miniMart.ui.flows.homeFlow.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.datasource.local.preferences.DataStorePref
import com.example.project_miniMart.domain.models.ProductDomain
import com.example.project_miniMart.domain.repositories.HomeTask
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.utils.uiManager.HomeEventManager
import com.example.project_miniMart.utils.uiManager.events.HomeEvent
import com.example.project_miniMart.widgets.listCategories.ChipCategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estado de la pantalla de Inicio.
 */
data class HomeUiState(
    val userName: String = "",
    val userEmail: String = "",
    val allProducts: List<ProductDomain> = emptyList(),
    val filteredProducts: List<ProductDomain> = emptyList(),
    val categories: List<ChipCategoryItem> = emptyList(),
    val isScrolling: Boolean = false,
    val showBottomSheet: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null
)

/**
 * ViewModel para la pantalla de Inicio.
 *
 * @param homeTask El caso de uso para obtener los productos.
 * @param dataStorePref El gestor de preferencias para obtener datos de usuario.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeTask: HomeTask,
    private val dataStorePref: DataStorePref
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    /**
     * Carga los datos iniciales de la pantalla (información de usuario y productos).
     */
    private fun loadInitialData() {
        viewModelScope.launch { fetchUserInfo() }
        viewModelScope.launch { fetchAllProducts() }
    }

    private suspend fun fetchUserInfo() {
        val userName = dataStorePref.getUserName.first()
        val userEmail = dataStorePref.getEmail.first()
        val nameSplit = userName.split(" ").filter { it.isNotBlank() }
        val first = nameSplit.firstOrNull() ?: ""
        val last = nameSplit.lastOrNull() ?: ""
        _uiState.update { it.copy(userName = "$first $last!", userEmail = userEmail) }
    }

    private suspend fun fetchAllProducts() {
        handleRequest(
            onLoading = { _uiState.update { it.copy(isLoading = true) } },
            call = { homeTask.fetchAllProducts() },
            onSuccess = { products ->
                val categories = mutableListOf(ChipCategoryItem(label = "All", isCheck = true))
                categories.addAll(
                    products.map { it.category }.distinct().map { ChipCategoryItem(label = it) }
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        allProducts = products,
                        filteredProducts = products,
                        categories = categories
                    )
                }
            },
            onError = { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error) }
            }
        )
    }

    /**
     * Filtra los productos por la categoría seleccionada.
     */
    fun selectCategory(categoryLabel: String) {
        val filteredList = if (categoryLabel == "All") {
            _uiState.value.allProducts
        } else {
            _uiState.value.allProducts.filter { it.category == categoryLabel }
        }

        _uiState.update {
            it.copy(
                categories = it.categories.map { cat -> cat.copy(isCheck = cat.label == categoryLabel) },
                filteredProducts = filteredList
            )
        }
    }

    /**
     * Actualiza el estado de visibilidad del BottomSheet.
     */
    fun toggleBottomSheet(show: Boolean) {
        _uiState.update { it.copy(showBottomSheet = show) }
    }

    /**
     * Oculta el diálogo de error y, si no hay datos, cierra la app.
     */
    fun dismissError() {
        _uiState.update { it.copy(errorMessage = null) }
        if (_uiState.value.categories.isEmpty() || _uiState.value.allProducts.isEmpty()) {
            HomeEventManager.triggerEvent(HomeEvent.FinishApp)
        }
    }
    
    /**
     * Actualiza el estado de scroll.
     */
    fun onGridScroll(isScrolling: Boolean) {
        _uiState.update { it.copy(isScrolling = isScrolling) }
    }
}
