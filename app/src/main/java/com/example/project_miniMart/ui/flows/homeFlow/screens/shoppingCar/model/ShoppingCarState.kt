package com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.model

import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity

data class ShoppingCarState(
    val userName: String = "",
    val listShoppingCar: List<GroupShopping> = emptyList(),
    val total: String = "",
    val showAlert: Boolean = false,
    val errorMassageAlert: Int = 0,
)
