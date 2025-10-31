package com.example.minimartapp.ui.screens.loginflow.unboardingComposeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.minimartapp.R
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoMediumSp35
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoRegularSp40
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoSp12
import com.example.minimartapp.ui.widgets.ButtonComposeView
import com.example.minimartapp.ui.widgets.TypesButtons


@Composable
fun UnboardingComposeView(
    onNavigateRegister: () -> Unit,
    onNavigateLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(80.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo), null,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(150.dp)
                )
                .size(150.dp)

        )

        Spacer(modifier = Modifier.height(25.dp))
        Text(
            "MiniMart",
            style = textStyleRobotoRegularSp40
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            "Welcome",
            style = textStyleRobotoMediumSp35
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            "Order fresch groceries online",
            style = textStyleRobotoSp12
        )
        Spacer(modifier = Modifier.height(50.dp))


        ButtonComposeView(
            typesButtons = TypesButtons.Primary,
            title = "Login",
        ) {
            onNavigateLogin.invoke()
        }
        Spacer(modifier = Modifier.height(25.dp))

        ButtonComposeView(
            typesButtons = TypesButtons.Secondary,
            title = "Sing up",
        ) {
            onNavigateRegister.invoke()
        }

    }
}