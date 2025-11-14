package com.example.minimartapp.ui.screens.loginflow.PasswordFlow.RecoverPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.minimartapp.R
import com.example.minimartapp.ui.screens.loginflow.PasswordFlow.RecoverPasswordViewModel
import com.example.minimartapp.ui.theme.DpSizes.dp16
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoRegularSp16
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.InputTextFieldComposeView
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.minimartapp.ui.widgets.TypesButtons


@Composable
fun RecoverPasswordComposeView(
    recoverViewModel: RecoverPasswordViewModel = hiltViewModel(),
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

                Icon(
                    painter = painterResource(R.drawable.ic_recover),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Reset your password",
                    style = textStyleRobotoRegularSp16
                )


                Spacer(modifier = Modifier.padding(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputTextFieldComposeView(
                        keyboardType = KeyboardType.Email,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeholder = "Enter your Email",
                        value = recoverViewModel.passwordInput
                    ) { valueChange ->
                        recoverViewModel.passwordInput = valueChange
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    ButtonComposeView(
                        typesButtons = TypesButtons.Primary,
                        title = "Enviar",
                    ) {
                        onNavigateLogin.invoke()
                    }
                }
            }
        }
    }
}
