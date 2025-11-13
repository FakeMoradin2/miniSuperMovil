package com.example.minimartapp.ui.screens.loginflow.RegisterFlow.register

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.minimartapp.R
import com.example.minimartapp.ui.screens.loginflow.RegisterFlow.RegisterViewModel
import com.example.minimartapp.ui.theme.DpSizes.dp16
import com.example.minimartapp.ui.theme.DpSizes.dp24
import com.example.minimartapp.ui.theme.DpSizes.dp28
import com.example.minimartapp.ui.theme.DpSizes.dp3
import com.example.minimartapp.ui.theme.DpSizes.dp4
import com.example.minimartapp.ui.theme.DpSizes.dp8
import com.example.minimartapp.ui.theme.Styles.TextStyleRobotoRMediumSp14
import com.example.minimartapp.ui.theme.Styles.TextStyleRobotoRegularSp14
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoBoldSp24
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoMediumSp12
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoRegularSp16
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.InputTextFieldComposeView
import com.example.minimartapp.ui.widgets.PasswordValidationComposeView
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.minimartapp.ui.widgets.TypesButtons

@Composable
fun RegisterComposeView(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onNavigateLogin: () -> Unit,
    onNavigateBack: () -> Unit,
) {

    // Estados para validación
    var showValidationErrors by remember { mutableStateOf(false) }

    // Validar si las contraseñas coinciden
    val doPasswordsMatch = registerViewModel.passwordRegisterInput == registerViewModel.confirmRegisterInput

    // Validaciones individuales de campos
    val isNameValid = registerViewModel.nameRegisterInput.isNotEmpty()
    val isPhoneValid = registerViewModel.phoneRegisterInput.isNotEmpty()
    val isPasswordValid = registerViewModel.passwordRegisterInput.isNotEmpty()
    val isConfirmPasswordValid = registerViewModel.confirmRegisterInput.isNotEmpty()

    // Validaciones específicas de contraseña
    val hasMinLength = registerViewModel.passwordRegisterInput.length >= 8
    val hasUpperCase = registerViewModel.passwordRegisterInput.any { it.isUpperCase() }
    val hasLowerCase = registerViewModel.passwordRegisterInput.any { it.isLowerCase() }
    val hasNumbers = registerViewModel.passwordRegisterInput.any { it.isDigit() }
    val hasSpecialChar = registerViewModel.passwordRegisterInput.any { !it.isLetterOrDigit() }

    // Calcular si el formulario es válido
    val isFormValid = isNameValid &&
            isPhoneValid &&
            isPasswordValid &&
            isConfirmPasswordValid &&
            doPasswordsMatch &&
            registerViewModel.checkBoxIsCheck &&
            hasMinLength && // Agregar validación de longitud mínima
            hasUpperCase && // Agregar validación de mayúscula
            hasLowerCase && // Agregar validación de minúscula
            hasNumbers &&   // Agregar validación de números
            hasSpecialChar  // Agregar validación de caracteres especiales

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarComposeView("") {
                onNavigateBack.invoke()
            }
        }
    ) { padding ->
        Surface {
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
                    "Create your account",
                    style = textStyleRobotoBoldSp24,
                    color = Color(0xFF2C3E50)
                )
                Spacer(modifier = Modifier.height(dp8))
                Text(
                    "Start shopping fresh groceries delivered to your door",
                    style = textStyleRobotoRegularSp16,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF5A6C7D)
                )
                Spacer(modifier = Modifier.height(dp28))

                // Campo de nombre con validación
                Column(modifier = Modifier.fillMaxWidth()) {
                    InputTextFieldComposeView(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Name",
                        placeholder = "Enter your Name",
                        value = registerViewModel.nameRegisterInput
                    ) { valueChange ->
                        registerViewModel.nameRegisterInput = valueChange
                    }

                    // Mensaje de error debajo del campo
                    if (showValidationErrors && !isNameValid) {
                        Text(
                            text = "Name cannot be empty",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dp16))

                // Campo de teléfono con validación
                Column(modifier = Modifier.fillMaxWidth()) {
                    InputTextFieldComposeView(
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Phone Number",
                        placeholder = "+1(555)123-4567",
                        value = registerViewModel.phoneRegisterInput
                    ) { valueChange ->
                        registerViewModel.phoneRegisterInput = valueChange
                    }

                    // Mensaje de error debajo del campo
                    if (showValidationErrors && !isPhoneValid) {
                        Text(
                            text = "Phone number cannot be empty",
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
                        placeholder = "Create a password",
                        isPassword = true,
                        value = registerViewModel.passwordRegisterInput
                    ) { valueChange ->
                        registerViewModel.passwordRegisterInput = valueChange
                    }

                    // Mensaje de error debajo del campo
                    if (showValidationErrors && !isPasswordValid) {
                        Text(
                            text = "Password cannot be empty",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dp8))

                // Componente de validación de contraseña con indicadores visuales
                PasswordValidationComposeView(
                    password = registerViewModel.passwordRegisterInput,
                    showValidation = true
                )

                Spacer(modifier = Modifier.height(dp8))

                // Campo de confirmar contraseña con validación
                Column(modifier = Modifier.fillMaxWidth()) {
                    InputTextFieldComposeView(
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier.fillMaxWidth(),
                        label = " Confirm Password",
                        placeholder = "Confirm your password",
                        isPassword = true,
                        value = registerViewModel.confirmRegisterInput
                    ) { valueChange ->
                        registerViewModel.confirmRegisterInput = valueChange
                    }

                    // Mensajes de error para confirmar contraseña
                    if (showValidationErrors) {
                        if (!isConfirmPasswordValid) {
                            Text(
                                text = "Please confirm your password",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        } else if (!doPasswordsMatch) {
                            Text(
                                text = "Passwords do not match",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dp16))

                // Checkbox con validación
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = registerViewModel.checkBoxIsCheck,
                            onCheckedChange = {
                                registerViewModel.checkBoxIsCheck = it
                            }
                        )
                        Spacer(modifier = Modifier.width(dp4))
                        Text(
                            "I agree to the Terms of Service and Privacy Policy ",
                            style = textStyleRobotoMediumSp12
                        )
                    }

                    // Mensaje de error para el checkbox
                    if (showValidationErrors && !registerViewModel.checkBoxIsCheck) {
                        Text(
                            text = "You must accept the terms to continue",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dp24))

                // Botón con validación
                ButtonComposeView(
                    typesButtons = TypesButtons.Primary,
                    isEnable = isFormValid,
                    title = "Create Account",
                ) {
                    if (isFormValid) {
                        // Aquí iría la lógica para registrar al usuario
                        // registerViewModel.registerUser()
                        onNavigateLogin.invoke()
                    } else {
                        // Mostrar errores de validación
                        showValidationErrors = true
                    }
                }

            }
        }
    }
}