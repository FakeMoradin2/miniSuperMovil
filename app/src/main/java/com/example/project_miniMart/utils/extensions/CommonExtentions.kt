package com.example.project_miniMart.utils.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity
import com.example.project_miniMart.domain.models.ProductDomain
import com.example.project_miniMart.ui.theme.DpSizes.Dp16
import com.example.project_miniMart.widgets.dsvouchertemplate.core.DsVoucherTemplate
import com.example.project_miniMart.widgets.dsvouchertemplate.main.builder.RenderWidget
import java.math.RoundingMode.DOWN
import java.text.DecimalFormat
import java.util.Locale

fun Double.formatMoney(): String {
    val format = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"))
    format.minimumFractionDigits = 2
    format.roundingMode = DOWN
    return format.format(this).trim()
}

fun ProductDomain.toEntity(): ShoppingEntity{
    return ShoppingEntity(
        id = 0,
        price = this.price,
        nameProducts = this.name,
        stock = this.stock,
        category = this.category
    )
}

@Composable
fun List<RenderWidget>.GetBitMapScreen(){
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .padding(Dp16), contentAlignment = Alignment.Center
    ) {
        DsVoucherTemplate {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                this@GetBitMapScreen.forEach { entry ->
                    entry.Render()
                }
            }
        }
    }
}