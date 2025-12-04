package com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
class DsVoucherSpacer : DsBaseModelVoucherWidgets<Dp>(){
    override var renderScreen: @Composable (Dp) -> Unit = {
        Spacer(modifier = Modifier.height(it))
    }
}