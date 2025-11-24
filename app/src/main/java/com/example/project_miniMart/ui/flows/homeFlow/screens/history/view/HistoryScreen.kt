package com.example.project_miniMart.ui.flows.homeFlow.screens.history.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.minimartapp.ui.widgets.TopBarComposeView
import com.example.project_miniMart.ui.flows.homeFlow.screens.history.HistoryViewModel
import com.example.project_miniMart.ui.flows.homeFlow.screens.history.intent.HistoryIntents

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel = hiltViewModel(), onBack: () -> Unit) {
    val state by historyViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarComposeView(
            "My history",
            "See your complete sales history."
        ) {
            onBack()
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.listSales) {
                HistoryItemComposeView(it) {
                    historyViewModel.channel.trySend(HistoryIntents.OnClickSale(it))
                }
            }
        }
    }
}