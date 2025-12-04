package com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.project_miniMart.R
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal


@Internal
class DsVoucherImageHeader : DsBaseModelVoucherWidgets<Modifier>() {
    override var renderScreen: @Composable (Modifier) -> Unit = {
        Image(
            painter = painterResource(id = R.drawable.ds_succes_voucher),
            contentDescription = null
        )
    }
}