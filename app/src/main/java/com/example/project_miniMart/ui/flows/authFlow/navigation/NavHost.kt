package com.example.project_miniMart.ui.flows.authFlow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project_miniMart.navigation.DestinationLogin
import com.example.project_miniMart.navigation.DestinationOnboarding
import com.example.project_miniMart.navigation.DestinationRegister
import com.example.project_miniMart.navigation.RecoveryPassword
import com.example.project_miniMart.ui.flows.authFlow.screens.login.view.LoginScreen
import com.example.project_miniMart.ui.flows.authFlow.screens.onboarding.OnboardingScreen
import com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.view.RecoveryPasswordScreen
import com.example.project_miniMart.ui.flows.authFlow.screens.register.view.RegisterScreen

/**
 * Define el gráfico de navegación para el flujo de autenticación.
 *
 * @param navController El controlador de navegación.
 */
@Composable
fun NavHostLogin(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DestinationOnboarding) {
        composable<DestinationOnboarding> { OnboardingScreen() }
        composable<DestinationLogin> { LoginScreen(navController) }
        composable<DestinationRegister> { RegisterScreen(navController) }
        composable<RecoveryPassword> { RecoveryPasswordScreen(navController) }
    }
}
