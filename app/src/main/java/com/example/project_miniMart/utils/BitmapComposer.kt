package com.example.project_miniMart.utils


import android.app.Activity
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.drawToBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.math.roundToInt


class BitmapComposer(
    private val mainScope: CoroutineScope,
) {
    suspend fun composableToBitmap(
        activity: Activity,
        width: Dp? = null,
        height: Dp? = null,
        screenDensity: Density,
        content: @Composable () -> Unit
    ): Bitmap = suspendCancellableCoroutine { continuation ->
        mainScope.launch {
            val contentWidthInPixels =
                (screenDensity.density * (width ?: 3000.dp).value).roundToInt()
            val contentHeightInPixels =
                (screenDensity.density * (height ?: 3000.dp).value).roundToInt()

            val composeViewContainer = FrameLayout(activity).apply {
                layoutParams =
                    ViewGroup.LayoutParams(contentWidthInPixels, contentHeightInPixels)
                visibility = View.INVISIBLE
            }

            val composeView = ComposeView(activity).apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
                setContent { content() }
            }

            composeViewContainer.addView(composeView)

            val decorView = activity.window.decorView as ViewGroup
            decorView.addView(composeViewContainer)

            val widthMeasureSpecs = if (width == null) {
                View.MeasureSpec.AT_MOST
            } else {
                View.MeasureSpec.EXACTLY
            }

            val heightMeasureSpecs = if (height == null) {
                View.MeasureSpec.AT_MOST
            } else {
                View.MeasureSpec.EXACTLY
            }

            Handler(Looper.getMainLooper()).post {
                composeViewContainer.measure(
                    View.MeasureSpec.makeMeasureSpec(
                        contentWidthInPixels,
                        widthMeasureSpecs
                    ),
                    View.MeasureSpec.makeMeasureSpec(
                        contentHeightInPixels,
                        heightMeasureSpecs
                    )
                )

                composeViewContainer.layout(0, 0, contentWidthInPixels, contentHeightInPixels)

                val bitmap = composeView.drawToBitmap()
                continuation.resume(bitmap)
                decorView.removeView(composeViewContainer)
            }
        }
    }
}