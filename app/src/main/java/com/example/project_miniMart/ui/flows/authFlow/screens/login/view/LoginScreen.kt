package com.example.project_miniMart.ui.flows.authFlow.screens.login.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.project_miniMart.ui.flows.authFlow.screens.login.LoginModel
import com.example.project_miniMart.ui.flows.authFlow.screens.login.intent.LoginIntents
import com.example.project_miniMart.ui.flows.authFlow.screens.login.view.composables.BodyLogin
import com.example.project_miniMart.ui.flows.authFlow.screens.login.view.composables.FooterLogin
import com.example.project_miniMart.ui.flows.authFlow.screens.login.view.composables.HeaderLogin


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginModel = hiltViewModel()
) {
    val state by loginViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarComposeView(
            "Login"
        ) {
            navController.popBackStack()
        }

        HeaderLogin()
        Spacer(modifier = Modifier.height(28.dp))
        BodyLogin(
            state = state,
            userChangeValue = { loginViewModel.channel.trySend(LoginIntents.UserNameChangeValue(it)) },
            passwordChangeValue = {
                loginViewModel.channel.trySend(
                    LoginIntents.PasswordChangeValue(
                        it
                    )
                )
            },
            checkBoxChangeValue = { loginViewModel.channel.trySend(LoginIntents.CheckBoxChecked(it)) },
            onClickButton = { loginViewModel.channel.trySend(LoginIntents.DoLogin) }
        )
        Spacer(modifier = Modifier.weight(1f))

        FooterLogin()

        if(state.showAlert) InfoDialog(onDismiss = {
            loginViewModel.channel.trySend(
                LoginIntents.HideAlert
            )
        }, "Login Failed", stringResource(state.errorMassageAlert), typeAlert = state.typeAlert!!)
    }
}