package com.example.minimartapp.ui.screens.loginflow.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.minimartapp.R
import com.example.minimartapp.datasource.comnom.ResponseStatus
import com.example.minimartapp.domain.models.UserDataDomain
import com.example.minimartapp.ui.screens.loginflow.LoginViewModel
import com.example.minimartapp.ui.theme.DpSizes.dp16
import com.example.minimartapp.ui.theme.DpSizes.dp24
import com.example.minimartapp.ui.theme.DpSizes.dp28
import androidx.compose.material3.MaterialTheme
import com.example.minimartapp.ui.theme.DpSizes.dp3
import com.example.minimartapp.ui.theme.DpSizes.dp30
import com.example.minimartapp.ui.theme.DpSizes.dp4
import com.example.minimartapp.ui.theme.Styles.TextStyleRobotoRMediumSp14
import com.example.minimartapp.ui.theme.Styles.TextStyleRobotoRegularSp14
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoBoldSp24
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoMediumSp12
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoRegularSp16
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.InputTextFieldComposeView
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.minimartapp.ui.widgets.TypesButtons

@Composable
fun LoginComposeView(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateRegister: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateRecoverPassword: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    val state = loginViewModel.observerState.collectAsState()
    val context = LocalContext.current

    // Estados para validación
    var isUsernameEmpty by remember { mutableStateOf(false) }
    var isPasswordEmpty by remember { mutableStateOf(false) }
    var showValidationErrors by remember { mutableStateOf(false) }

    // Función para validar si el formulario es válido
    fun validateForm(): Boolean {
        val isUsernameValid = loginViewModel.nameLoginInput.isNotEmpty()
        val isPasswordValid = loginViewModel.passwordLoginInput.isNotEmpty()
        val isCheckboxChecked = loginViewModel.checkBoxIsCheck

        isUsernameEmpty = !isUsernameValid
        isPasswordEmpty = !isPasswordValid
        showValidationErrors = true

        return isUsernameValid && isPasswordValid && isCheckboxChecked
    }

    when (state.value) {
        is ResponseStatus.Error<*> -> {
            val message = stringResource((state.value as ResponseStatus.Error<*>).message)
            Log.e("ERROR", message)
        }

        is ResponseStatus.Loading<*> -> {
            Log.e("LOADING", "LOADING")
        }

        is ResponseStatus.Success<*> -> {
            val userName = (state.value as ResponseStatus.Success<UserDataDomain>).data.userName
            val phone = (state.value as ResponseStatus.Success<UserDataDomain>).data.phone
            val rol = (state.value as ResponseStatus.Success<UserDataDomain>).data.rol

            Log.e("SUCCESS", userName)
            Log.e("SUCCESS", phone)
            Log.e("SUCCESS", rol)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarComposeView("") {
                onNavigateBack.invoke()
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp16)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(150.dp))
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(dp24))

            Text(
                "Welcome back", style = textStyleRobotoBoldSp24,
                color = Color(0xFF2C3E50)
            )
            Spacer(modifier = Modifier.height(dp30))
            Text(
                "Sign to your account to continue shopping",
                style = textStyleRobotoRegularSp16,
                textAlign = TextAlign.Center,
                color = Color(0xFF5A6C7D)
            )

            Spacer(modifier = Modifier.height(dp28))

            // Campo de usuario con validación
            Column(modifier = Modifier.fillMaxWidth()) {
                InputTextFieldComposeView(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Username",
                    placeholder = "Enter your Username",
                    value = loginViewModel.nameLoginInput,
                ) { valueChange ->
                    loginViewModel.nameLoginInput = valueChange
                    // Limpiar error cuando el usuario empiece a escribir
                    if (isUsernameEmpty && valueChange.isNotEmpty()) {
                        isUsernameEmpty = false
                    }
                }

                // Mensaje de error debajo del campo
                if (showValidationErrors && isUsernameEmpty) {
                    Text(
                        text = "Username cannot be empty",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(dp16))

            // Campo de contraseña con validación
            Column(modifier = Modifier.fillMaxWidth()) {
                InputTextFieldComposeView(
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Password",
                    placeholder = "Enter your Password",
                    isPassword = true,
                    value = loginViewModel.passwordLoginInput,
                ) { valueChange ->
                    loginViewModel.passwordLoginInput = valueChange
                    // Limpiar error cuando el usuario empiece a escribir
                    if (isPasswordEmpty && valueChange.isNotEmpty()) {
                        isPasswordEmpty = false
                    }
                }

                // Mensaje de error debajo del campo
                if (showValidationErrors && isPasswordEmpty) {
                    Text(
                        text = "Password cannot be empty",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(dp16))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = loginViewModel.checkBoxIsCheck,
                    onCheckedChange = {
                        loginViewModel.checkBoxIsCheck = it
                    })
                Spacer(modifier = Modifier.width(dp4))
                Text(
                    "Remember me",
                    style = textStyleRobotoMediumSp12
                )
            }

            // Mensaje de error para el checkbox
            if (showValidationErrors && !loginViewModel.checkBoxIsCheck) {
                Text(
                    text = "You must accept the terms to continue",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(dp16))

            // Determinar si el botón debe estar habilitado
            val isButtonEnabled = loginViewModel.nameLoginInput.isNotEmpty() &&
                    loginViewModel.passwordLoginInput.isNotEmpty() &&
                    loginViewModel.checkBoxIsCheck

            ButtonComposeView(
                typesButtons = TypesButtons.Primary,
                title = "Sign In",
                isEnable =  isButtonEnabled
            ) {
                // Validar antes de proceder
                if (validateForm()) {
                    loginViewModel.fetchLogin()
                    onNavigateToHome.invoke()
                }
            }

            Spacer(modifier = Modifier.height(dp16))

            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "¿Forgot your password?",
                    style = textStyleRobotoMediumSp12,
                    color = Color(0xFF64B5F6),
                    modifier = Modifier.clickable
                    {
                        onNavigateRecoverPassword.invoke()
                    }
                )
            }
            Spacer(modifier = Modifier.height(120.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dp24),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Text("Don't have a account? ", style = TextStyleRobotoRegularSp14)
                Spacer(modifier = Modifier.width(dp3))
                Text(
                    text = "Sign up",
                    style = TextStyleRobotoRMediumSp14,
                    color = Color(0xFF64B5F6),
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        onNavigateRegister.invoke()
                    })
                )
            }
        }
    }
}