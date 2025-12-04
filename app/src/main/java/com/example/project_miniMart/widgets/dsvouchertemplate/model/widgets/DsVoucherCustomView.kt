package com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets

import androidx.compose.runtime.Composable
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
class DsVoucherCustomView : DsBaseModelVoucherWidgets<@Composable () -> Unit>() {
    override var renderScreen: @Composable (@Composable () -> Unit) -> Unit = {
        it.invoke()
    }
}