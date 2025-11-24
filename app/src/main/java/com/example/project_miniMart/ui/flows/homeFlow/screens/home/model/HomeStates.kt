package com.example.project_miniMart.ui.flows.homeFlow.screens.home.model

import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.project_miniMart.domain.models.ProductDomain
import com.example.project_miniMart.widgets.listCategories.ChipCategoryItem

data class HomeStates(
    val userName: String = "",
    val userEmail: String = "",
    val allProducts: List<ProductDomain> = emptyList(),
    val allCategories: List<ChipCategoryItem> = emptyList(),
    val showBottomSheet: Boolean = false,
    val showAlert: Boolean = false,
    val errorMassageAlert: Int = 0,
    val typeAlert: TypeAlert? = null,
    val isScrolling: Boolean = false,
    val showBottomSheetAccount: Boolean = false

)