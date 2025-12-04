package com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoBoldSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoBoldSp24
import com.example.project_miniMart.ui.theme.gray800Color
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
class DsVoucherLabelFormatMoney : DsBaseModelVoucherWidgets<List<String>>(){
    override var renderScreen: @Composable (List<String>) -> Unit = {
        Row {
            Text(
                text = it[0], style = textStyleRobotoBoldSp24,
                color = gray800Color
            )
            Text(
                text = it[1], style = textStyleRobotoBoldSp16,
                color = gray800Color
            )
        }
    }
}