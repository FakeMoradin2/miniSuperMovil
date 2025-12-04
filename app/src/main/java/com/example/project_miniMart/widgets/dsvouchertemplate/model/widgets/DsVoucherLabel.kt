package com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal

data class LabelModelVoucher(
    val textResource:String,
    val textStyle:TextStyle,
)

@Internal
class DsVoucherLabel : DsBaseModelVoucherWidgets<LabelModelVoucher>() {
    override var renderScreen: @Composable (LabelModelVoucher) -> Unit = { labelModel ->
        Text(text = labelModel.textResource, style = labelModel.textStyle)
    }
}