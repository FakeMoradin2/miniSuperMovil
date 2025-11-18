package com.example.project_miniMart.ui.flows.homeFlow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project_miniMart.navigation.DestinationHome
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.view.HomeScreen

/**
 * Define el gráfico de navegación para el flujo principal de la aplicación.
 *
 * @param navController El controlador de navegación.
 */
@Composable
fun NavHostHome(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DestinationHome) {
        composable<DestinationHome> {
            HomeScreen()
        }
    }
}
