package com.example.minimartapp.ui.screens.loginflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minimartapp.ui.screens.loginflow.RegisterFlow.register.RegisterComposeView
import com.example.minimartapp.ui.screens.loginflow.login.LoginComposeView
import com.example.minimartapp.ui.screens.loginflow.unboardingComposeView.UnboardingComposeView

@Composable
fun LoginFlowNavHost() {
    val navcontroller = rememberNavController()


    NavHost(navController = navcontroller, startDestination = DestinationUnboarding) {
        composable<DestinationUnboarding> {
            UnboardingComposeView (
                onNavigateLogin = {
                    navcontroller.navigate(DestinationLogin)
                },
                onNavigateRegister = {
                    navcontroller.navigate(DestinationRegister)
                }

            )
        }

        composable<DestinationLogin> {
            LoginComposeView (
                onNavigateRegister = {
                    navcontroller.navigate(DestinationRegister)
                },
                onNavigateBack = {
                    navcontroller.popBackStack()
                }
            )
        }

        composable<DestinationRegister> {
            RegisterComposeView(
                onNavigateLogin = {
                    navcontroller.navigate(DestinationLogin)
                }
            ) {
                navcontroller.popBackStack()
            }
        }
    }
}