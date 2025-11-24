package com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.local.bd.entities.GroupShopping
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.Styles.roboto10Medium
import com.example.project_miniMart.ui.theme.Styles.roboto14Regular
import com.example.project_miniMart.ui.theme.Styles.roboto16Medium
import com.example.project_miniMart.utils.extensions.formatMoney

@Composable
fun ListProductCarItem(shoppingEntity: GroupShopping, onDeleteClick:(GroupShopping)-> Unit) {
    Column{
        Row(
            modifier = Modifier
                .clickable{
                    onDeleteClick(shoppingEntity)
                }
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        RoundedCornerShape(80.dp)
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                Text(shoppingEntity.nameProducts, style = roboto14Regular)
                Text("X${shoppingEntity.totalStock}", style = roboto10Medium, color = AccentDark)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(shoppingEntity.totalPrice.formatMoney(), style = roboto16Medium, color = AccentDark)
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(start = 64.dp, end = 16.dp).height(1.dp).background(Color.DarkGray))
    }
}