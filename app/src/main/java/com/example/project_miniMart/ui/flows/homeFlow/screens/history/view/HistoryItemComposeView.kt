package com.example.project_miniMart.ui.flows.homeFlow.screens.history.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.local.bd.entities.SaleItemEntity
import com.example.project_miniMart.datasource.local.bd.entities.SaleWithItems
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.Styles.roboto10Medium
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoBoldSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp12
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoThinSp12

@Composable
fun HistoryItemComposeView(
    item: SaleWithItems,
    onClick: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = checkId(item.sale.id)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(88.dp),
        onClick = {
            onClick.invoke(item.sale.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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
            Column(modifier = Modifier.weight(1f)) {
                Text(item.sale.createAt, style = textStyleRobotoBoldSp16, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Total: ", style = textStyleRobotoThinSp12)
                    Text(item.sale.total, style = textStyleRobotoMediumSp12)
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text("${getNumProducts(item.items)} productos", style = roboto10Medium)
                }
            }
        }
    }
}


private fun getNumProducts(list: List<SaleItemEntity>): Int {
    return list.sumOf { it.quantity }
}

private fun checkId(id: Int): Color {
    return if (id % 2 == 0) Color.White else Color(0xFFC8EAFA)
}