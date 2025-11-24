package com.example.project_miniMart.ui.flows.homeFlow.screens.account.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.project_miniMart.ui.flows.homeFlow.screens.account.AccountViewModel


@Composable
fun AccountScreen(accountViewModel: AccountViewModel = hiltViewModel(), onBack: () -> Unit){
    val state by accountViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarComposeView(
            "My Account",
            "Look at your account"
        ) {
            onBack()
        }
    }
}