package com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.intent

sealed class VoucherIntents {
    data class GetDataOfVoucher(val saleId: Int) : VoucherIntents()
    data object FinishFlow: VoucherIntents()
}