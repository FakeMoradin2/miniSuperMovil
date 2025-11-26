package com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.domain.repositories.HomeTask
import com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail.Intent.SaleDetailIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.saleDetail.model.SaleDetailStates
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.intent.VoucherIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.voucher.model.VoucherStates
import com.example.project_miniMart.utils.handleRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleDetailViewModel @Inject constructor(private val homeTask: HomeTask) : ViewModel() {
    private val _state = MutableStateFlow(SaleDetailStates())
    val state: StateFlow<SaleDetailStates> = _state


    val channel = Channel<SaleDetailIntents>(Channel.BUFFERED)

    init {
        setupIntents()
    }

    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        is SaleDetailIntents.GetDataOfVoucher -> setupDataVoucher(it.saleId)
                    }
                }
        }
    }

    private suspend fun setupDataVoucher(saleInt: Int){
        handleRequest(
            call = {homeTask.getSaleWithItemsById(saleInt)},
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