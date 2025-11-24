package com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.minimartapp.ui.widgets.InfoDialog
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.ShoppingCarViewModel
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.intent.ShoppingCarIntent
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.view.components.ListProductCarItem
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.Styles.roboto16Medium
import com.example.project_miniMart.ui.theme.Styles.roboto20Medium
import com.example.project_miniMart.widgets.newswipeable.SwipeButtonComposeView

@Composable
fun ShoppingCarScreen(
    navController: NavController,
    shoppingCarViewModel: ShoppingCarViewModel = hiltViewModel()
) {
    val state by shoppingCarViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarComposeView(
            "My Car",
            state.userName
        ) {
            navController.popBackStack()
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(state.listShoppingCar) {
                ListProductCarItem(
                    shoppingEntity = it
                ) {
                    shoppingCarViewModel.channel.trySend(ShoppingCarIntent.ShowAlert(it))
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total:", style = roboto16Medium)
            Spacer(modifier = Modifier.weight(1f))
            Text(state.total, style = roboto20Medium, color = AccentDark)
        }
        SwipeButtonComposeView(
            Modifier.padding(16.dp),
            state.listShoppingCar.isNotEmpty(),
            onComplete = {
                shoppingCarViewModel.channel.trySend(ShoppingCarIntent.CreateNewSale)
            },
            setReset = {})
    }

    if (state.showAlert) InfoDialog(
        onDismiss = {
            shoppingCarViewModel.channel.trySend(ShoppingCarIntent.HideAlert)
        },
        "Delete from my Shopping car",
        stringResource(state.errorMassageAlert),
        typeAlert = TypeAlert.ERROR
    ) {
        shoppingCarViewModel.channel.trySend(ShoppingCarIntent.CancelAlert)
    }
}