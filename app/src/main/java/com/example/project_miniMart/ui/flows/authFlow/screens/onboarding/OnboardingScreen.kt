package com.example.project_miniMart.ui.flows.authFlow.screens.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.minimartapp.ui.widgets.CardInformation
import com.example.project_miniMart.ui.flows.authFlow.navigation.DestinationLogin
import com.example.project_miniMart.ui.flows.authFlow.navigation.DestinationRegister
import com.example.project_miniMart.ui.flows.authFlow.screens.onboarding.componets.PagerAutoScroll
import com.example.project_miniMart.ui.theme.PrimaryLight
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent
import com.example.project_miniMart.widgets.heder.NavigationHeaderComposeView


@Composable
fun OnboardingScreen() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (header, list, login, register) = createRefs()


        NavigationHeaderComposeView(
            titleView = "Welcome to MiniMart",
            descriptionView = "Shop smart. Order fresh food and track deliveries — all from your phone.",
            modifier = Modifier.constrainAs(header) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            })


        PagerAutoScroll(modifier = Modifier.constrainAs(list) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(header.bottom, margin = 24.dp)
        })

        CardInformation(
            color = PrimaryLight,
            text = "Login",
            description = "You can sign in with your existing account.",
            modifier = Modifier.constrainAs(login) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(list.bottom, margin = 24.dp)
            },
            onClick = {
                AuthEventManager.triggerEvent(AuthEvent.NavigateTo(DestinationLogin))
            }
        )


        CardInformation(
            text = "Sing up",
            description = "You can create a new account to get started.",
            modifier = Modifier.constrainAs(register) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(login.bottom, margin = 24.dp)
            },
            onClick = {
                AuthEventManager.triggerEvent(AuthEvent.NavigateTo(DestinationRegister))
            }
        )
    }
}
