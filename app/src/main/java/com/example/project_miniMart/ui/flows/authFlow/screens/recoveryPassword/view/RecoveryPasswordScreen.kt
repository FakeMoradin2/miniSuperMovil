package com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.DsTextField
import com.example.minimartapp.ui.widgets.InfoDialog
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.minimartapp.ui.widgets.TypesButtons
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.viewmodel.RecoveryPasswordViewModel
import com.example.project_miniMart.ui.theme.Typography

/**
 * Pantalla para la recuperación de contraseña.
 *
 * @param navController El controlador de navegación.
 * @param recoveryPasswordViewModel El ViewModel para esta pantalla.
 */
@Composable
fun RecoveryPasswordScreen(
    navController: NavController,
    recoveryPasswordViewModel: RecoveryPasswordViewModel = hiltViewModel()
) {
    val uiState by recoveryPasswordViewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarComposeView(stringResource(R.string.recovery_password_title)) {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.recovery_password_description),
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            DsTextField(
                value = uiState.email,
                onValueChange = recoveryPasswordViewModel::onEmailChange,
                placeholder = stringResource(R.string.email_placeholder),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = !uiState.email.validEmail(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ButtonComposeView(
                isEnable = uiState.isButtonEnabled,
                typesButtons = TypesButtons.Primary,
                title = stringResource(R.string.recover_password_button),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                recoveryPasswordViewModel.onRecoverClick()
            }

            Spacer(modifier = Modifier.height(16.dp))

            ButtonComposeView(
                typesButtons = TypesButtons.Secondary,
                title = stringResource(R.string.back_to_login_button),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                navController.popBackStack()
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        uiState.successMessage?.let {
            InfoDialog(
                onDismiss = recoveryPasswordViewModel::hideSuccessMessage,
                title = stringResource(R.string.recovery_success_title),
                message = stringResource(it),
                typeAlert = TypeAlert.SUCCESS
            )
        }

        uiState.errorMessage?.let {
            InfoDialog(
                onDismiss = recoveryPasswordViewModel::hideErrorMessage,
                title = stringResource(R.string.recovery_error_title),
                message = stringResource(it),
                typeAlert = TypeAlert.ERROR
            )
        }
    }
}
