package com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.minimartapp.ui.widgets.DottedLineComposeView
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
class DsVoucherDottedLine : DsBaseModelVoucherWidgets<Modifier>() {
    override var renderScreen: @Composable (Modifier) -> Unit = {
        DottedLineComposeView(
            modifier = it
        )
    }
}