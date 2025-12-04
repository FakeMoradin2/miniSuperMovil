package com.example.project_miniMart.ui.flows.homeFlow.screens.history.model

import com.example.project_miniMart.datasource.local.bd.entities.SaleWithItems

data class HistoryStates(
    val listSales: List<SaleWithItems> = emptyList()
)
