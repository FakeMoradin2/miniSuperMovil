package com.example.project_miniMart.ui.flows.authFlow.screens.register.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.CheckBoxComposeView
import com.example.minimartapp.ui.widgets.DsTextField
import com.example.minimartapp.ui.widgets.InfoDialog
import com.example.minimartapp.ui.widgets.PasswordDsTextField
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.minimartapp.ui.widgets.TypesButtons
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.flows.authFlow.screens.register.viewmodel.RegisterUiState
import com.example.project_miniMart.ui.flows.authFlow.screens.register.viewmodel.RegisterViewModel

/**
 * Pantalla de Registro de usuario.
 *
 * @param navController El controlador de navegación para volver a la pantalla anterior.
 * @param registerViewModel El ViewModel que maneja la lógica de registro.
 */
@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarComposeView(stringResource(R.string.register_title)) {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(28.dp))

            BodyRegister(
                uiState = uiState,
                onNameChange = registerViewModel::onNameChange,
                onEmailChange = registerViewModel::onEmailChange,
                onPhoneChange = registerViewModel::onPhoneChange,
                onPasswordChange = registerViewModel::onPasswordChange,
                onConfirmPasswordChange = registerViewModel::onConfirmPasswordChange,
                onTermsCheckedChange = registerViewModel::onTermsCheckedChange
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                ButtonComposeView(
                    isEnable = uiState.isButtonEnabled,
                    typesButtons = TypesButtons.Primary,
                    title = stringResource(R.string.create_account),
                ) {
                    registerViewModel.onRegisterClick()
                }
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        uiState.errorMessage?.let {
            InfoDialog(
                onDismiss = registerViewModel::hideError,
                title = stringResource(R.string.register_error_title),
                message = stringResource(it),
                typeAlert = TypeAlert.ERROR
            )
        }
    }
}

/**
 * Cuerpo de la pantalla de registro con los campos de entrada de datos.
 */
@Composable
private fun BodyRegister(
    uiState: RegisterUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onTermsCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DsTextField(
            value = uiState.name,
            onValueChange = onNameChange,
            placeholder = stringResource(R.string.name_placeholder)
        )
        Spacer(modifier = Modifier.height(16.dp))
        DsTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            placeholder = stringResource(R.string.email_placeholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = !uiState.isEmailValid
        )
        Spacer(modifier = Modifier.height(16.dp))
        DsTextField(
            value = uiState.phoneNumber,
            onValueChange = onPhoneChange,
            placeholder = stringResource(R.string.phone_placeholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = !uiState.isPhoneValid
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordDsTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            placeholder = stringResource(R.string.password_placeholder)
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordDsTextField(
            value = uiState.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            placeholder = stringResource(R.string.confirm_password_placeholder),
            isError = !uiState.arePasswordsMatching
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CheckBoxComposeView(
                checked = uiState.isTermsChecked,
                onCheckedChange = onTermsCheckedChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.terms_and_conditions))
        }
    }
}
