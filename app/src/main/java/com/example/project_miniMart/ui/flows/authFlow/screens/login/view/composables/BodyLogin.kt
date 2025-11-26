package com.example.project_miniMart.ui.flows.authFlow.screens.login.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.InputTextFieldComposeView
import com.example.minimartapp.ui.widgets.TypesButtons
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoBOLDSp14
import com.example.project_miniMart.ui.flows.authFlow.navigation.RecoveryPassword
import com.example.project_miniMart.ui.flows.authFlow.screens.login.model.LoginStates
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent

@Composable
fun BodyLogin(state: LoginStates,
              userChangeValue: (String) -> Unit,
              passwordChangeValue: (String) -> Unit,
              checkBoxChangeValue: (Boolean) -> Unit,
              onClickButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextFieldComposeView(
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth(),
            label = "User name",
            placeholder = "Enter your User name",
            value = state.userName,
        ) { valueChange ->
            userChangeValue(valueChange)
        }
        Spacer(modifier = Modifier.height(16.dp))
        InputTextFieldComposeView(
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth(),
            label = "Password",
            placeholder = "Enter your password",
            isPassword = true,
            value = state.password,
        ) { valueChange ->
            passwordChangeValue(valueChange)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isCheckBoxChecked,
                onCheckedChange = { onValueChange ->
                    checkBoxChangeValue(onValueChange)
                })
            Spacer(modifier = Modifier.width(4.dp))
            Text("Remember me")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComposeView(
            isEnable = state.isEnableButton,
            typesButtons = TypesButtons.Primary,
            title = "Sign In",
        ) {
           onClickButton()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "Forgot your password?",
                style = textStyleRobotoBOLDSp14,
                color = Color(0xFF64B5F6),
                modifier = Modifier.clickable(true){
                    AuthEventManager.triggerEvent(AuthEvent.NavigateTo(RecoveryPassword))
                }
            )
        }
    }
}