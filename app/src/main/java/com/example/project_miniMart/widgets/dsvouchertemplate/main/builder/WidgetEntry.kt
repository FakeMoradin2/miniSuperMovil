package com.example.project_miniMart.widgets.dsvouchertemplate.main.builder

import androidx.compose.runtime.Composable
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
class WidgetEntry<T>(
    val widget: DsBaseModelVoucherWidgets<T>,
    private val data: T
) : RenderWidget {
    @Composable
    override fun Render() {
        widget.renderScreen(data)
    }
}