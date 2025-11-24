package com.example.project_miniMart.ui.flows.homeFlow.screens.history.intent

sealed class HistoryIntents {
    data object ShowSaleHistory : HistoryIntents()
    data class OnClickSale (val saleId: Int) : HistoryIntents()
}