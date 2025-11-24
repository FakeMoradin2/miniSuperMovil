package com.example.project_miniMart.ui.flows.authFlow.screens.register.view.composables

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.minimartapp.ui.widgets.InputTextFieldComposeView
import com.example.minimartapp.ui.widgets.LabelsValidations
import com.example.project_miniMart.ui.flows.authFlow.screens.register.model.RegisterStates
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp14

@Composable
fun ContentRegister(
    state: RegisterStates,
    nameChangeValue: (String) -> Unit,
    emailChangeValue: (String) -> Unit,
    phoneChangeValue: (String) -> Unit,
    passwordChangeValue: (String) -> Unit,
    confirmPasswordChangeValue: (String) -> Unit,
    checkBoxChangeValue: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextFieldComposeView(
            modifier = Modifier.fillMaxWidth(),
            label = "Name",
            placeholder = "Enter your name",
            value = state.name
        ) {
            nameChangeValue(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        InputTextFieldComposeView(
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth(),
            label = "Email",
            placeholder = "john@example.com",
            value = state.email,
            isError = state.isErrorEmail,
            textError = "The email is invalid"
        ) {
            emailChangeValue(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        InputTextFieldComposeView(
            keyboardType = KeyboardType.Phone,
            modifier = Modifier.fillMaxWidth(),
            label = "Phone Number",
            placeholder = "+1 (555) 123-4567",
            value = state.phoneNumber,
            isError = state.isErrorPhone,
            textError = "The phone is not valid"
        ) {
            phoneChangeValue(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        InputTextFieldComposeView(
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth(),
            label = "Password",
            placeholder = "Create a password",
            isPassword = true,
            value = state.password
        ) {
            passwordChangeValue(it)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LabelsValidations(state)
        Spacer(modifier = Modifier.height(8.dp))
        InputTextFieldComposeView(
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth(),
            label = "Confirm Password",
            placeholder = "Confirm your password",
            isPassword = true,
            value = state.confirmPassword,
            isError = !state.passWordIsEqual,
            textError = "The passwords are not equals"
        ) {
            confirmPasswordChangeValue(it)
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.isCheckBoxChecked,
                onCheckedChange = {
                    checkBoxChangeValue(it)
                })
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "I agree to the Terms of Service and Privacy Policy",
                style = textStyleRobotoMediumSp14
            )
        }
    }
}