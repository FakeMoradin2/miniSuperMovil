package com.example.project_miniMart.ui.flows.authFlow.screens.register.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoBoldSp24
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoRegularSp16

@Composable
fun HeaderRegister() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            null,
            modifier = Modifier
                .size(80.dp)
                .clip(
                    RoundedCornerShape(80.dp)
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Create your account", style = textStyleRobotoBoldSp24)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Start shopping fresh groceries delivered to your door",
            style = textStyleRobotoRegularSp16,
            textAlign = TextAlign.Center
        )
    }
}