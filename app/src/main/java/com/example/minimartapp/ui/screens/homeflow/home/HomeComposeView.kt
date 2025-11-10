package com.example.minimartapp.ui.screens.homeflow.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.minimartapp.R
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoBoldSp24
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoMediumSp35
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoRegularSp12
import com.example.minimartapp.ui.widgets.ButtonCategorieComposeView
import com.example.minimartapp.ui.widgets.ButtonMenuComposeView
import com.example.minimartapp.ui.widgets.TypesButtons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComposeView(
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Menu, contentDescription = "") },
                text = { Text("") },
                onClick = {
                    showBottomSheet = true
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                expandedHeight = 100.dp,
                actions = {
                    Row() {
                        Icon(
                            modifier = Modifier.offset(y = (-40).dp),
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = null,
                            tint = Color.White,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            modifier = Modifier.offset(y = (-40).dp),
                            painter = painterResource(R.drawable.ic_car),
                            contentDescription = null,
                            tint = Color.White,

                            )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                },
                title = {
                    Column {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            "Welcome!",
                            style = textStyleRobotoMediumSp35
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            "Explore,enjoy,and make the most of the app",
                            style = textStyleRobotoRegularSp12
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF64B5F6),
                    titleContentColor = Color.White
                )
            )
        }

    ) { padding ->
        Surface(
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Categories",
                    style = textStyleRobotoBoldSp24,
                    color = Color(0xFF64B5F6),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Spacer(modifier = Modifier.width(10.dp))
                    ButtonCategorieComposeView(
                        typesButtons = TypesButtons.Primary,
                        title = "All",
                    ) {

                    }

                    Spacer(modifier = Modifier.width(6.dp))
                    ButtonCategorieComposeView(
                        typesButtons = TypesButtons.Secondary,
                        title = "Dairy"
                    ) {

                    }

                    Spacer(modifier = Modifier.width(6.dp))
                    ButtonCategorieComposeView(
                        typesButtons = TypesButtons.Secondary,
                        title = "Bakery",
                    ) {

                    }

                    Spacer(modifier = Modifier.width(6.dp))
                    ButtonCategorieComposeView(
                        typesButtons = TypesButtons.Secondary,
                        title = "Drinks",
                    ) {}

                }
                Spacer(modifier = Modifier.height(20.dp))









                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState,
                        containerColor = Color(0xFFF2F2F7)
                    ) {
                        Text(
                            text = "Menu",
                            style = textStyleRobotoBoldSp24,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Sheet content
                        ButtonMenuComposeView(
                            typesButtons = TypesButtons.Terseary,
                            title = "My account",
                            icon = R.drawable.ic_account
                            ) {
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        ButtonMenuComposeView(
                            typesButtons = TypesButtons.Terseary,
                            title = "Order History",
                            icon = R.drawable.ic_history
                            ) {
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        ButtonMenuComposeView(
                            typesButtons = TypesButtons.Terseary,
                            title = "Setting",
                            icon = R.drawable.ic_settings
                            ) {
                        }
                        Spacer(modifier = Modifier.height(30.dp))

                        ButtonMenuComposeView(
                            typesButtons = TypesButtons.Terseary,
                            title = "Help",
                            icon = R.drawable.ic_help
                            ) {

                        }

                    }
                }
            }

        }
    }
}