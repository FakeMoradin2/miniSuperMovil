package com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.ui.theme.DpSizes.Dp24
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoBoldSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoRegularSp12
import com.example.project_miniMart.ui.theme.black200Color
import com.example.project_miniMart.ui.theme.gray800Color
import com.example.project_miniMart.ui.theme.gray900Color
import com.example.project_miniMart.widgets.dsvouchertemplate.main.builder.DsVoucherBuilder
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.ScreenShotVisibility

@Composable
fun buildHeader(
    builder: DsVoucherBuilder,
    amount: List<String>,
    date: String
): DsVoucherBuilder = builder
    .addImageHeader(Modifier, ScreenShotVisibility.GONE)
    .addSpacer(8.dp, ScreenShotVisibility.GONE)
    .addLabel(
        "Transaction Receipt",
        style = textStyleRobotoBoldSp16.copy(color = gray900Color),
        screenVisibility = ScreenShotVisibility.GONE
    )
    .addSpacer(screenVisibility = ScreenShotVisibility.GONE)
    .addLabelFormatMoney(amount)
    .addLabel(
        "Grand Total",
        textStyleRobotoRegularSp12.copy(color = gray800Color)
    )
    .addSpacer()
    .addLabel(
        date,
        style = textStyleRobotoMediumSp16.copy(color = black200Color)
    )
    .addSpacer(4.dp)
    .addLabel(
        "Transaction Date",
        style = textStyleRobotoRegularSp12.copy(color = gray800Color)
    )
    .addSpacer()
    .addDottedLine(
        Modifier
            .fillMaxWidth()
    )
    .addSpacer(Dp24)