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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.minimartapp.ui.widgets.DividerComposeView
import com.example.project_miniMart.ui.flows.authFlow.navigation.DestinationRegister
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp14
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoRegularSp14
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent

@Composable
fun FooterLogin() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DividerComposeView()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have account?", style = textStyleRobotoRegularSp14)
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                "Sign up",
                style = textStyleRobotoMediumSp14,
                color = Color(0xFF64B5F6),
                modifier = Modifier.clickable {
                    AuthEventManager.triggerEvent(AuthEvent.NavigateTo(DestinationRegister))
                })
        }
    }
}