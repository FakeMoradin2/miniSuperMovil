package com.example.project_miniMart.ui.flows.homeFlow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.project_miniMart.ui.flows.homeFlow.screens.account.model.AccountStates
import com.example.project_miniMart.ui.flows.homeFlow.screens.account.view.AccountScreen
import com.example.project_miniMart.ui.flows.homeFlow.screens.history.view.HistoryScreen
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.view.HomeScreen
import com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail.view.SaleDetailScreen
import com.example.project_miniMart.ui.flows.homeFlow.screens.shoppingCar.view.ShoppingCarScreen
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.view.VoucherScreen

@Composable
fun NavHostHome(navController: NavHostController) {

    NavHost(navController = navController, startDestination = DestinationHome) {
        composable<DestinationHome> {
            HomeScreen()
        }

        composable<DestinationShoppingCar> {
            ShoppingCarScreen(navController)
        }
        composable<DestinationHistory> {
            HistoryScreen{
                navController.popBackStack()
            }
        }

        composable<DestinationVoucher> { backStackEntry->
            val destination = backStackEntry.toRoute<DestinationVoucher>()
            VoucherScreen(destination.saleId)
        }

        composable <DestinationSaleDetail> {
            val destination = it.toRoute<DestinationSaleDetail>()
            SaleDetailScreen(destination.saleId)
            {
                navController.popBackStack()
            }
        }

        composable<DestinationAccount> {
            AccountScreen {
                navController.popBackStack()
            }
        }
    }
}