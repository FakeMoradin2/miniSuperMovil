package com.example.minimartapp.ui.screens.homeflow.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage
import com.example.minimartapp.R
import com.example.minimartapp.ui.theme.DpSizes.dp1
import com.example.minimartapp.ui.theme.DpSizes.dp100
import com.example.minimartapp.ui.theme.DpSizes.dp16
import com.example.minimartapp.ui.theme.DpSizes.dp8
import com.example.minimartapp.ui.theme.DpSizes.dp80
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoBoldSp24
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoThinSp10
import com.example.minimartapp.ui.theme.Styles.textStyleRobotobOLDSp10
import com.example.minimartapp.ui.widgets.DividerComposeView
import com.example.minimartapp.ui.widgets.TopBarComposeView

data class ModelProducts(
    val img: Int,
    val name: String,
    val stock: Int,
    val price: Double
)

@Composable
fun HomeComposeView(
    myList: List<ModelProducts>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(modifier = Modifier.fillMaxWidth()) {
                TopBarComposeView("Welcome John Doe")
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Image(painter = painterResource(R.drawable.ic_car), null)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Image(painterResource(R.drawable.ic_add), null)
            }
        }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dp16)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Products on sale",
                    style = textStyleRobotoBoldSp24,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(dp16))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(myList.size) {
                        Column(
                            modifier = Modifier.width(dp100),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = myList.get(it).img,
                                contentDescription = "Azúcar",
                                contentScale = ContentScale.Crop,
                            )
                            Text(
                                myList.get(it).name, style = textStyleRobotoThinSp10, color = Color(
                                    0xFF176589
                                )
                            )
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text("Price: ", style = textStyleRobotoThinSp10)
                                Text("$${myList.get(it).price}", style = textStyleRobotobOLDSp10)
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text("Stock: ", style = textStyleRobotoThinSp10)
                                Text("${myList.get(it).stock}", style = textStyleRobotobOLDSp10)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(dp16))
                DividerComposeView(text = "All products")
                Spacer(modifier = Modifier.height(dp16))
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(), colors = CardDefaults.cardColors(
                        containerColor = Color(0xCCECEBEB)
                    )
                ) {
                    repeat(myList.size) {
                        Column(
                            modifier = Modifier.padding(dp16),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(myList.get(it).img),
                                    null,
                                    modifier = Modifier.size(dp80),
                                    contentScale = ContentScale.Crop,
                                )
                                Column {
                                    Row(modifier = Modifier) {
                                        Text("Price: ", style = textStyleRobotoThinSp10)
                                        Text(
                                            "$${myList.get(it).price}",
                                            style = textStyleRobotobOLDSp10
                                        )
                                    }
                                    Row(modifier = Modifier) {
                                        Text("Stock: ", style = textStyleRobotoThinSp10)
                                        Text(
                                            "${myList.get(it).stock}",
                                            style = textStyleRobotobOLDSp10
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(onClick = {}) {
                                    Image(painter = painterResource(R.drawable.ic_shopping), null)
                                }
                            }
                            Spacer(modifier = Modifier.height(dp8))
                            HorizontalDivider(thickness = dp1, modifier = Modifier.fillMaxWidth())
                        }
                    }
                    repeat(myList.size) {
                        Column(
                            modifier = Modifier.padding(dp16),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(myList.get(it).img),
                                    null,
                                    modifier = Modifier.size(dp80),
                                    contentScale = ContentScale.Crop,
                                )
                                Column {
                                    Row(modifier = Modifier) {
                                        Text("Price: ", style = textStyleRobotoThinSp10)
                                        Text(
                                            "$${myList.get(it).price}",
                                            style = textStyleRobotobOLDSp10
                                        )
                                    }
                                    Row(modifier = Modifier) {
                                        Text("Stock: ", style = textStyleRobotoThinSp10)
                                        Text(
                                            "${myList.get(it).stock}",
                                            style = textStyleRobotobOLDSp10
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(onClick = {}) {
                                    Image(painter = painterResource(R.drawable.ic_shopping), null)
                                }
                            }
                            Spacer(modifier = Modifier.height(dp8))
                            HorizontalDivider(thickness = dp1, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }
}