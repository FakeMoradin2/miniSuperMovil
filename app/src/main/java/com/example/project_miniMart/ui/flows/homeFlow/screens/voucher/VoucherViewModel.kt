package com.example.project_miniMart.ui.flows.homeFlow.screens.voucher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.domain.repositories.HomeTask
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.intent.VoucherIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.model.VoucherStates
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
class VoucherViewModel @Inject constructor(private val homeTask: HomeTask) : ViewModel() {
    private val _state = MutableStateFlow(VoucherStates())
    val state: StateFlow<VoucherStates> = _state


    val channel = Channel<VoucherIntents>(Channel.BUFFERED)

    init {
        setupIntents()
    }
    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        is VoucherIntents.GetDataOfVoucher -> setupDataVoucher(it.saleId)
                        VoucherIntents.FinishFlow -> finishFlow()
                    }
                }
        }
    }

    private suspend fun finishFlow() {
        handleRequest(
            call = { homeTask.deleteAllCar() },
            onSuccess = {
                HomeEventManager.triggerEvent(HomeEvent.RefreshApp)
            },
            onError = {
                // TODO: mostrar alerta de que no se pudo eliminar el carrito y regresar al home
            }
        )
    }

    private suspend fun setupDataVoucher(saleInt: Int) {
        handleRequest(
            call = { homeTask.getSaleWithItemsById(saleInt) },
            onSuccess = {
                it.let {
                    _state.value = _state.value.copy(dataVoucher = it)
                }
            },
            onError = {
                //mostrar alerta de que no se pudo obtener la informacion de la venta
            }
        )
    }
}