package com.example.project_miniMart.ui.flows.homeFlow.screens.account

import androidx.lifecycle.ViewModel
import com.example.project_miniMart.ui.flows.homeFlow.screens.account.model.AccountStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(AccountStates())
    val state: StateFlow<AccountStates> = _state


}