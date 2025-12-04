package com.example.project_miniMart.widgets.dsvouchertemplate.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.ui.theme.DpSizes.Dp24
import com.example.project_miniMart.ui.theme.gray100Color
import com.example.project_miniMart.ui.theme.gray200Color

/**
 *@param content this param allow add components into the template
 *
 */
@Composable
fun DsVoucherTemplate(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawRect(
                    color = gray100Color,
                    size = size
                )

                val circleRadius = 4.dp.toPx()
                val circleSpacing = 6.dp.toPx()

                var startX = 0f
                while (startX < size.width) {
                    drawCircle(
                        color = gray200Color,
                        radius = circleRadius,
                        center = Offset(startX + circleRadius, size.height)
                    )
                    startX += circleRadius * 2 + circleSpacing
                }
            }
    ) {
        content()
    }
}

/**
 * This widget allow add an elevation to DsVoucherTemplate
 *
 */
@Composable
fun DsVoucherElevation() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dp24)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        gray200Color,
                        Color.Transparent
                    )
                )
            )
    )
}