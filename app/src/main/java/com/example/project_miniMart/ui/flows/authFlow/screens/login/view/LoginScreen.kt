package com.example.project_miniMart.ui.flows.authFlow.screens.login.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.flows.authFlow.screens.login.view.composables.BodyLogin
import com.example.project_miniMart.ui.flows.authFlow.screens.login.view.composables.FooterLogin
import com.example.project_miniMart.ui.flows.authFlow.screens.login.view.composables.HeaderLogin
import com.example.project_miniMart.ui.flows.authFlow.screens.login.viewmodel.LoginViewModel

/**
 * Pantalla de Login.
 *
 * @param navController El controlador de navegación.
 * @param loginViewModel El ViewModel para la pantalla de login.
 */
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
    ) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarComposeView(stringResource(R.string.login_title)) {
                navController.popBackStack()
            }

            HeaderLogin()
            Spacer(modifier = Modifier.height(28.dp))
            BodyLogin(
                uiState = uiState,
                onUserNameChange = loginViewModel::onUserNameChange,
                onPasswordChange = loginViewModel::onPasswordChange,
                onCheckBoxChange = loginViewModel::onCheckBoxChange,
                onLoginClick = loginViewModel::doLogin
                )
            Spacer(modifier = Modifier.weight(1f))

            FooterLogin()
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        uiState.errorMessage?.let {
            InfoDialog(
                onDismiss = loginViewModel::hideError,
                title = stringResource(R.string.login_error_title),
                message = stringResource(it),
                typeAlert = TypeAlert.ERROR
            )
        }
    }
}
