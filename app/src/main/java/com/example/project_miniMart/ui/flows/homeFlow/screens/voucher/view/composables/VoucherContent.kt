package com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.view.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.TypesButtons
import com.example.project_miniMart.datasource.local.bd.entities.SaleWithItems
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.model.VoucherStates
import com.example.project_miniMart.utils.extensions.GetBitMapScreen
import com.example.project_miniMart.utils.extensions.formatMoney
import com.example.project_miniMart.utils.getCurrentDateFormatted
import com.example.project_miniMart.utils.processBitMap
import com.example.project_miniMart.widgets.dsvouchertemplate.main.builder.DsVoucherBuilder
import com.example.project_miniMart.widgets.dsvouchertemplate.main.composables.DsBaseVoucherTemplate
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DataModel
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.InfoDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Composable
fun VoucherContent(states: SaleWithItems?, onFinish: () -> Unit) {
    val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    val currentActivity = LocalContext.current
    val screenDensity: Density = LocalDensity.current
    val compositionCoroutineScope: CoroutineScope = rememberCoroutineScope()


    val total = states?.sale?.total?.split(".") ?: listOf("0", "00")
    val date = states?.sale?.createAt ?: ""
    val folio = states?.sale?.folio ?: ""

    val infoData = InfoDataModel(
        title = "Voucher Information",
        dataModel = states?.items?.map { item ->
            DataModel(
                key = item.nameProducts + " — Qty: ${item.quantity}",
                value = item.subtotal.formatMoney()
            )
        }.orEmpty()
    )

    val builder = DsVoucherBuilder().apply {
        buildHeader(this, total, date)
        buildFooter(this, infoData, folio)
    }

    DsBaseVoucherTemplate(
        contentVoucher = {
            builder.build().invoke()
        },
        contentFooter = {
            ButtonComposeView(
                modifier = Modifier.fillMaxWidth(),
                typesButtons = TypesButtons.Secondary,
                title = "Share",
            ) {
                compositionCoroutineScope.launch {
                    processBitMap(it, mainScope, currentActivity, screenDensity) {
                        builder.getVisibleWidgets().GetBitMapScreen()
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ButtonComposeView(
                modifier = Modifier.fillMaxWidth(),
                typesButtons = TypesButtons.Primary,
                title = "Finish",
            ) {
                onFinish()
            }
        }
    )
}