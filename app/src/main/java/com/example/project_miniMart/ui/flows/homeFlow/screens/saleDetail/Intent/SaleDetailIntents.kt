package com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail.Intent

sealed class SaleDetailIntents {
    data class GetDataOfVoucher(val saleId: Int) : SaleDetailIntents()
    data object FinishFlow : SaleDetailIntents()

}