package com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.InfoDialog
import com.example.minimartapp.ui.widgets.InputTextFieldComposeView
import com.example.minimartapp.ui.widgets.TypesButtons
import com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.RecoveryPasswordViewModel
import com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.intent.RecoveryPasswordIntents
import com.example.project_miniMart.widgets.heder.NavigationHeaderComposeView

@Composable
fun RecoveryPasswordScreen(
    navController: NavHostController,
    recoveryPasswordViewModel: RecoveryPasswordViewModel = hiltViewModel()
) {
    val state by recoveryPasswordViewModel.state.collectAsStateWithLifecycle()

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (header, email, send, cancel) = createRefs()


        NavigationHeaderComposeView(
            titleView = "Forgot your password?",
            descriptionView = "Don’t worry. Enter your email to reset your password and regain access to your account.",
            modifier = Modifier.constrainAs(header) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            })

        InputTextFieldComposeView(
            keyboardType = KeyboardType.Email,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(email) {
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    top.linkTo(header.bottom, margin = 24.dp)
                    width = Dimension.fillToConstraints
                },
            label = "Email",
            placeholder = "john@example.com",
            value = state.email,
            isError = state.isErrorEmail,
            textError = "The email is invalid"
        ) {
            recoveryPasswordViewModel.channel.trySend(
                RecoveryPasswordIntents.EmailChangeValue(
                    it
                )
            )
        }

        ButtonComposeView(
            isEnable = state.isEnableButton,
            typesButtons = TypesButtons.Primary,
            title = "Password Recovery",
            modifier = Modifier.constrainAs(send) {
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(email.bottom, margin = 24.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            recoveryPasswordViewModel.channel.trySend(
                RecoveryPasswordIntents.RecoveryPassword
            )
        }
        ButtonComposeView(
            typesButtons = TypesButtons.Secondary,
            title = "Back to Login",
            modifier = Modifier.constrainAs(cancel) {
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(send.bottom, margin = 24.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            navController.popBackStack()
        }

        if(state.showAlert) InfoDialog(onDismiss = {
            recoveryPasswordViewModel.channel.trySend(
                RecoveryPasswordIntents.HideAlert
            )
        }, "Recovery Password", stringResource(state.errorMassageAlert), typeAlert = state.typeAlert!!)
    }
}