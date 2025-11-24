package com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail.Intent.SaleDetailIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail.SaleDetailViewModel
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.view.composables.VoucherContent


@Composable
fun SaleDetailScreen(saleId: Int, saleDetailViewModel: SaleDetailViewModel = hiltViewModel() ,onClickBack:()-> Unit ) {
    val state by saleDetailViewModel.state.collectAsStateWithLifecycle()
    saleDetailViewModel.channel.trySend(SaleDetailIntents.GetDataOfVoucher(saleId))

    state.dataVoucher?.let {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBarComposeView(
                "Sale Details",
                "View all information about this sale."
            ) {
             onClickBack.invoke()
            }

            VoucherContent(state.dataVoucher ) {
                saleDetailViewModel.channel.trySend(SaleDetailIntents.FinishFlow)
            }
        }
    }
}