package com.example.project_miniMart.ui.flows.homeFlow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.view.HomeScreen

@Composable
fun NavHostHome(navController: NavHostController) {

    NavHost(navController = navController, startDestination = DestinationHome) {
        composable<DestinationHome> {
            HomeScreen()
        }
    }
}