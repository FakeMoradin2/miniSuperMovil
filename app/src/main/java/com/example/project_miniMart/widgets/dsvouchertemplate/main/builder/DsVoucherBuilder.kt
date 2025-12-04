package com.example.project_miniMart.widgets.dsvouchertemplate.main.builder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.ScreenShotVisibility
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DsVoucherCustomView
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DsVoucherDottedLine
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DsVoucherImageHeader
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DsVoucherInfoData
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DsVoucherLabel
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DsVoucherLabelFormatMoney
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.DsVoucherSpacer
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.InfoDataModel
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.LabelModelVoucher


class DsVoucherBuilder {
    private val contentList = mutableListOf<RenderWidget>()

    fun addLabel(
        text: String,
        style: TextStyle,
        screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE
    ) = apply {
        contentList.add(WidgetEntry(DsVoucherLabel().apply {
            this.screenVisibility = screenVisibility
        }, LabelModelVoucher(text, style)))
    }

    fun addSpacer(
        dimen: Dp = 16.dp,
        screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE
    ) = apply {
        val spacer = DsVoucherSpacer().apply {
            this.screenVisibility = screenVisibility
        }
        contentList.add(WidgetEntry(spacer, dimen))
    }

    fun addImageHeader(
        modifier: Modifier,
        screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE
    ) = apply {
        val image = DsVoucherImageHeader().apply {
            this.screenVisibility = screenVisibility
        }
        contentList.add(WidgetEntry(image, modifier))
    }

    fun addLabelFormatMoney(
        list: List<String>,
        screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE
    ) = apply {
        val label = DsVoucherLabelFormatMoney().apply {
            this.screenVisibility = screenVisibility
        }
        contentList.add(WidgetEntry(label, list))
    }

    fun addDottedLine(
        modifier: Modifier,
        screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE
    ) = apply {
        val dottedLine = DsVoucherDottedLine().apply {
            this.screenVisibility = screenVisibility
        }
        contentList.add(WidgetEntry(dottedLine, modifier))
    }


    fun addCustomComposable(
        content: @Composable () -> Unit,
        screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE
    ) = apply {
        val customWidget = DsVoucherCustomView().apply {
            this.screenVisibility = screenVisibility
        }
        contentList.add(WidgetEntry(customWidget, content))
    }

    fun addInFoData(
        listData: InfoDataModel,
        screenVisibility: ScreenShotVisibility = ScreenShotVisibility.VISIBLE,
    )= apply {
        val infoData = DsVoucherInfoData().apply {
            this.screenVisibility = screenVisibility
        }
        contentList.add(WidgetEntry(infoData, listData))
    }

    fun getVisibleWidgets(): List<RenderWidget> {
        return contentList.filter {
            it is WidgetEntry<*> && it.widget.screenVisibility == ScreenShotVisibility.VISIBLE
        }
    }

    fun build(): @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            contentList.forEach { widget ->
                widget.Render()
            }
        }
    }
}