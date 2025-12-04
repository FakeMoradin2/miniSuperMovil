package com.example.project_miniMart.widgets.dsvouchertemplate.main.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.ui.theme.DpSizes.Dp16
import com.example.project_miniMart.ui.theme.DpSizes.Dp24
import com.example.project_miniMart.ui.theme.DpSizes.Dp8
import com.example.project_miniMart.widgets.dsvouchertemplate.core.DsVoucherTemplate


@Composable
fun DsBaseVoucherTemplate(
    contentVoucher: @Composable () -> Unit,
    contentFooter: @Composable (Dp) -> Unit
) {
    val configuration = LocalConfiguration.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = Dp16,
                end = Dp16,
                top = Dp24,
                bottom = Dp24
            )
            .clip(RoundedCornerShape(Dp8))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .clip(RoundedCornerShape(Dp8))
        ) {
            DsVoucherTemplate {
                contentVoucher.invoke()
            }
            Spacer(
                modifier = Modifier.heightIn(16.dp).weight(1f)
            )
            contentFooter.invoke(configuration.screenWidthDp.dp)
        }
    }
}