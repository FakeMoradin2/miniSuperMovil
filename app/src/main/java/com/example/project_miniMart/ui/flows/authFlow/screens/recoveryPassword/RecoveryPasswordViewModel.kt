package com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.project_miniMart.R
import com.example.project_miniMart.domain.repositories.LoginTask
import com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.intent.RecoveryPasswordIntents
import com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.model.RecoveryPasswordStates
import com.example.project_miniMart.utils.extensions.validEmail
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.widgets.loader.DsLoaderView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor(private val loginTask: LoginTask) : ViewModel() {
    private val _state = MutableStateFlow(RecoveryPasswordStates())
    val state: StateFlow<RecoveryPasswordStates> = _state


    val channel = Channel<RecoveryPasswordIntents>(Channel.BUFFERED)

    init {
        setupIntents()
    }


    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        is RecoveryPasswordIntents.EmailChangeValue -> (onValueEmailChange(it.dataString))
                        RecoveryPasswordIntents.HideAlert -> _state.value = _state.value.copy(
                            showAlert = false,
                            errorMassageAlert = 0,
                            typeAlert = null
                        )
                        RecoveryPasswordIntents.RecoveryPassword -> toDoRecoveryPassword()
                    }
                }
        }
    }

    private fun onValueEmailChange(value: String) {
        _state.value = _state.value.copy(
            email = value,
            isErrorEmail = !value.validEmail(),
            isEnableButton = value.validEmail()
        )
    }

    private suspend fun toDoRecoveryPassword() {
        DsLoaderView.showLoader()
        handleRequest(
            call = { loginTask.recoveryPassword(_state.value.email) },
            onSuccess = {
                _state.value = _state.value.copy(
                    showAlert = true,
                    errorMassageAlert = R.string.success_recovery,
                    typeAlert = TypeAlert.SUCCESS
                )
            },
            onError = {
                _state.value = _state.value.copy(
                    showAlert = true,
                    errorMassageAlert = it,
                    typeAlert = TypeAlert.ERROR
                )
            }
        )
    }
}