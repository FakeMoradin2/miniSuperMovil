package com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.VoucherViewModel
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.intent.VoucherIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.view.composables.VoucherContent
import com.example.project_miniMart.widgets.heder.NavigationHeaderComposeView

@Composable
fun VoucherScreen(saleId: Int, voucherViewModel: VoucherViewModel = hiltViewModel()) {
    BackHandler(enabled = true) {}
    val state by voucherViewModel.state.collectAsStateWithLifecycle()
    voucherViewModel.channel.trySend(VoucherIntents.GetDataOfVoucher(saleId))

    state.dataVoucher?.let {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavigationHeaderComposeView(
                titleView = "Your Voucher Is Ready",
                descriptionView = "Review the details of your completed transaction below.",
                modifier = Modifier)

            VoucherContent(state.dataVoucher){
                voucherViewModel.channel.trySend(VoucherIntents.FinishFlow)
            }
        }
    }
}