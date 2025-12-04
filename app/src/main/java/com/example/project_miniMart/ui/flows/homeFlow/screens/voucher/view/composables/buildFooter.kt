package com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoRegularSp12
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoRegularSp14
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoThinSp12
import com.example.project_miniMart.widgets.dsvouchertemplate.main.builder.DsVoucherBuilder
import com.example.project_miniMart.widgets.dsvouchertemplate.model.widgets.InfoDataModel

@Composable
fun buildFooter(
    builder: DsVoucherBuilder,
    infoDataModel: InfoDataModel,
    folio: String
): DsVoucherBuilder =
    builder
        .addCustomComposable(content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = folio, style = textStyleRobotoMediumSp16)
                Text("Folio", style = textStyleRobotoRegularSp14)
            }
        })
        .addSpacer()
        .addInFoData(infoDataModel)
        .addSpacer()
        .addCustomComposable(content = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_logo),
                    null,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(
                            RoundedCornerShape(80.dp)
                        )
                        .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(80.dp))

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "For any questions or support, please contact our customer service team.",
                    style = textStyleRobotoThinSp12,
                    textAlign = TextAlign.Center
                )
            }
        })
        .addSpacer()