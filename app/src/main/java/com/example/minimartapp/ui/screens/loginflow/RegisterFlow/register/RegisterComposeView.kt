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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onNavigateBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            TopBarComposeView("") {
//add accion de regreso
                onNavigateBack.invoke()
            }
        }) { padding ->
        Surface(
        ) {
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

                Text("Create your account",
                    style = textStyleRobotoBoldSp24,
                    color = Color(0xFF2C3E50))
                Spacer(modifier = Modifier.height(dp8))
                Text(
                    "Start shopping fresh groceries delivered to your door",
                    style = textStyleRobotoRegularSp16,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF5A6C7D)
                )
                Spacer(modifier = Modifier.height(dp28))

                InputTextFieldComposeView(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Name",
                    placeholder = "Enter your Name",
                    value = registerViewModel.nameRegisterInput
                ) { valueChange ->
                    registerViewModel.nameRegisterInput = valueChange
                }

                Spacer(modifier = Modifier.height(dp16))
                InputTextFieldComposeView(
                    keyboardType = KeyboardType.Phone,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Phone Number",
                    placeholder = "+1(555)123-4567",
                    value = registerViewModel.phoneRegisterInput
                ) { valueChange ->
                    registerViewModel.phoneRegisterInput = valueChange
                }

                Spacer(modifier = Modifier.height(dp16))
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
                Spacer(modifier = Modifier.height(dp8))
                PasswordValidationComposeView(isValid = false)
                Spacer(modifier = Modifier.height(dp8))

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
                Spacer(modifier = Modifier.height(dp16))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = registerViewModel.checkBoxIsCheck, onCheckedChange = {
                            registerViewModel.checkBoxIsCheck = it
                        })
                    Spacer(modifier = Modifier.width(dp4))
                    Text(
                        "I gree to the Terms of Service and Privacy Policy ",
                        style = textStyleRobotoMediumSp12
                    ) // agregar un stilo de letra
                }
                Spacer(modifier = Modifier.height(dp24))
                ButtonComposeView(
                    typesButtons = TypesButtons.Primary,
                    title = "Create Account",
                ) {

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dp24),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Already have an account? ", style = TextStyleRobotoRegularSp14)
                    Spacer(modifier = Modifier.width(dp3))
                    Text(
                        "sign in",
                        style = TextStyleRobotoRMediumSp14,
                        color = Color(0xFF64B5F6),
                        modifier = Modifier.clickable(enabled = true, onClick = {
                            onNavigateLogin.invoke()
                        })
                    )
                }
            }
        }
    }
}
