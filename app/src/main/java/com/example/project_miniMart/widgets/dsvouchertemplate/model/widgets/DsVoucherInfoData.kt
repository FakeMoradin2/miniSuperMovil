package com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.project_miniMart.ui.theme.DpSizes.Dp12
import com.example.project_miniMart.ui.theme.DpSizes.Dp16
import com.example.project_miniMart.ui.theme.DpSizes.Dp4
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoBoldSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoRegularSp14
import com.example.project_miniMart.ui.theme.gray900Color
import com.example.project_miniMart.widgets.dsvouchertemplate.model.base.DsBaseModelVoucherWidgets
import org.jetbrains.annotations.ApiStatus.Internal

data class InfoDataModel(
    val title: String,
    val dataModel: List<DataModel>
)

data class DataModel(
    val key: String,
    val value: String
)

@Internal
class DsVoucherInfoData : DsBaseModelVoucherWidgets<InfoDataModel>() {
    override val renderScreen: @Composable (InfoDataModel) -> Unit
        get() = { data ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = data.title,
                    style = textStyleRobotoMediumSp16,
                    color = gray900Color
                )
                Spacer(modifier = Modifier.height(Dp12))
                repeat(data.dataModel.size){
                    Column(modifier = Modifier.fillMaxWidth().padding(top = Dp16)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = data.dataModel[it].key,
                                style = textStyleRobotoRegularSp14,
                                color = gray900Color,
                            )
                            Spacer(modifier = Modifier.width(Dp4))
                            Text(
                                text = data.dataModel[it].value, style = textStyleRobotoBoldSp16,
                                color = gray900Color,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }
        }
}