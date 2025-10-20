package com.example.minimartapp.ui.screens.loginflow.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.minimartapp.ui.screens.loginflow.LoginViewModel
import com.example.minimartapp.ui.theme.DpSizes.dp16
import com.example.minimartapp.ui.theme.DpSizes.dp24
import com.example.minimartapp.ui.theme.DpSizes.dp28
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
    onNavigateBack: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarComposeView("MiniMart") {
                onNavigateBack.invoke()
            }
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dp16)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome back", style = textStyleRobotoBoldSp24)
                Spacer(modifier = Modifier.height(dp30))
                Text(
                    "Sign to your account to continue shopping",
                    style = textStyleRobotoRegularSp16,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(dp28))
                InputTextFieldComposeView(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Username",
                    placeholder = "Enter your Username",
                    value = loginViewModel.nameLoginInput,
                ) { valueChange ->
                    loginViewModel.nameLoginInput = valueChange
                }

                Spacer(modifier = Modifier.height(dp16))
                InputTextFieldComposeView(
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Password",
                    placeholder = "Enter your Password",
                    isPassword = true,
                    value = loginViewModel.passwordLoginInput,
                ) { valueChange ->
                    loginViewModel.passwordLoginInput = valueChange
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
                    ) // agregar un stilo de letra
                }
                Spacer(modifier = Modifier.height(dp16))
                ButtonComposeView(
                    typesButtons = TypesButtons.Primary,
                    title = "Sign In",
                ) {

                }

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
                        "Sign up",
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
}