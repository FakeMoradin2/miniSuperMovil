package com.example.minimartapp.ui.screens.homeflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minimartapp.ui.screens.homeflow.home.HomeComposeView


@Composable
fun HomeFlowNavHost() {
    val navcontroller = rememberNavController()

    NavHost(navController = navcontroller, startDestination = DestinationHome) {
        composable<DestinationHome> {
           HomeComposeView()

        }
    }
}