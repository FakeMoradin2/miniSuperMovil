package com.example.project_miniMart.ui.flows.homeFlow.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.domain.repositories.HomeTask
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationSaleDetail
import com.example.project_miniMart.ui.flows.homeFlow.screens.history.intent.HistoryIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.history.model.HistoryStates
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.utils.uiManager.HomeEventManager
import com.example.project_miniMart.utils.uiManager.events.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: HomeTask) : ViewModel() {
    private val _state = MutableStateFlow(HistoryStates())
    val state: StateFlow<HistoryStates> = _state

    val channel = Channel<HistoryIntents>(Channel.BUFFERED)

    init {
        channel.trySend(HistoryIntents.ShowSaleHistory)
        setupIntents()
    }


    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        HistoryIntents.ShowSaleHistory -> getAllSale()
                        is HistoryIntents.OnClickSale -> {
                            HomeEventManager.triggerEvent(HomeEvent.NavigateTo(DestinationSaleDetail(it.saleId)))
                        }
                    }
                }
        }
    }

    private suspend fun getAllSale(){
        handleRequest(
            call = {repository.getALLSaleList()},
            onSuccess = { listWithData ->
                _state.value = _state.value.copy(listSales = listWithData)
            },
            onError = {

            }
        )
    }
}