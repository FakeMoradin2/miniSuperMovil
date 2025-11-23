package com.example.project_miniMart.ui.flows.authFlow.screens.register.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.InfoDialog
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.minimartapp.ui.widgets.TypesButtons
import com.example.project_miniMart.ui.flows.authFlow.screens.register.RegisterViewModel
import com.example.project_miniMart.ui.flows.authFlow.screens.register.intent.RegisterIntents
import com.example.project_miniMart.ui.flows.authFlow.screens.register.view.composables.ContentRegister
import com.example.project_miniMart.ui.flows.authFlow.screens.register.view.composables.HeaderRegister

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val state by registerViewModel.state.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarComposeView(
            "Register",
            "Please complete the form below to register a new account."
        ) {
            navController.popBackStack()
        }

        HeaderRegister()
        Spacer(modifier = Modifier.height(28.dp))
        ContentRegister(
            state = state,
            nameChangeValue = {
                registerViewModel.channel.trySend(
                    RegisterIntents.NameChangeValue(
                        it
                    )
                )
            },
            emailChangeValue = {
                registerViewModel.channel.trySend(
                    RegisterIntents.EmailChangeValue(
                        it
                    )
                )
            },
            phoneChangeValue = {
                registerViewModel.channel.trySend(
                    RegisterIntents.PhoneChangeValue(
                        it
                    )
                )
            },
            passwordChangeValue = {
                registerViewModel.channel.trySend(
                    RegisterIntents.PasswordChangeValue(
                        it
                    )
                )
            },
            confirmPasswordChangeValue = {
                registerViewModel.channel.trySend(
                    RegisterIntents.ConfirmPasswordChangeValue(
                        it
                    )
                )
            },
            checkBoxChangeValue = {
                registerViewModel.channel.trySend(
                    RegisterIntents.CheckBoxChecked(
                        it
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            ButtonComposeView(
                isEnable = state.isEnableButton,
                typesButtons = TypesButtons.Primary,
                title = "Create Account",
            ) {
                registerViewModel.channel.trySend(
                    RegisterIntents.DoRegister
                )
            }
        }

        if(state.showAlert) InfoDialog(onDismiss = {
            registerViewModel.channel.trySend(
                RegisterIntents.HideAlert
            )
        }, "Register Failed", stringResource(state.errorMassageAlert), typeAlert = state.typeAlert!!)
    }
}