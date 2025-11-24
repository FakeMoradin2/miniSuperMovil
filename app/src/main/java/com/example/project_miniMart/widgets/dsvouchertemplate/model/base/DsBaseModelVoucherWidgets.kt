package com.example.project_miniMart.widgets.dsvouchertemplate.model.base

import androidx.compose.runtime.Composable
import org.jetbrains.annotations.ApiStatus.Internal

sealed class ScreenShotVisibility {
    data object GONE : ScreenShotVisibility()
    data object VISIBLE : ScreenShotVisibility()
}

@Internal
open class DsBaseModelVoucherWidgets<T> {
    open val renderScreen: @Composable (T) -> Unit = {}
    open var screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE
}
