package com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent

import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping

sealed class ShoppingCarIntent {
    data object GetAllShoppingCar : ShoppingCarIntent()
    data class DeleteProductFromCar(val productName: String, val category: String) :
        ShoppingCarIntent()

    data object HideAlert : ShoppingCarIntent()
    data object CancelAlert : ShoppingCarIntent()
    data class ShowAlert(val productToDelete: GroupShopping) : ShoppingCarIntent()
    data object CreateNewSale : ShoppingCarIntent()

}