package com.example.project_miniMart.ui.flows.homeFlow.screens.home.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.local.bd.entities.ShoppingEntity
import com.example.project_miniMart.domain.models.ProductDomain
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.Styles.roboto10Medium
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp12
import com.example.project_miniMart.utils.extensions.formatMoney
import com.example.project_miniMart.utils.extensions.toEntity
import com.example.project_miniMart.widgets.listCategories.ButtonPill


@Composable
fun ProductItemComposeView(productDomain: ProductDomain, addProduct: (ShoppingEntity) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .height(160.dp)
            .width(140.dp)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        onClick = {}
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
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
            Spacer(modifier = Modifier.height(4.dp))
            Text(productDomain.name, style = textStyleRobotoMediumSp12)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Text(
                    "Price: ${productDomain.price.formatMoney()}",
                    style = roboto10Medium,
                    color = AccentDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Stock: ${productDomain.stock}",
                    style = roboto10Medium,
                    color = getColorOnStock(productDomain.stock)
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    ButtonPill("Add", isEnable = productDomain.stock >= 1) {
                        addProduct(productDomain.toEntity())
                    }
                }
            }
        }
    }
}

private fun getColorOnStock(stock: Int): Color {
    return when (stock) {
        0 -> Color(0xff990B0B)
        in 1..5 -> Color(0xffE2A201)
        else -> Color(0xff0F990B)
    }
}