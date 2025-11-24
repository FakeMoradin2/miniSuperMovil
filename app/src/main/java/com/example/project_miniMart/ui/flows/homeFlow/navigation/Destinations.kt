package com.example.project_miniMart.ui.flows.homeFlow.navigation

import kotlinx.serialization.Serializable

@Serializable
object DestinationHome

@Serializable
object DestinationShoppingCar

@Serializable
object DestinationHistory

@Serializable
object DestinationAccount
@Serializable
data class DestinationVoucher(
    val saleId: Int
)

@Serializable
data class DestinationSaleDetail(
    val  saleId: Int
)
