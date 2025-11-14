package com.example.project_miniMart.ui.flows.authFlow.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.project_miniMart.datasource.local.preferences.DataStorePref
import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.domain.models.UserDataDomain
import com.example.project_miniMart.domain.repositories.LoginTask
import com.example.project_miniMart.ui.flows.authFlow.screens.login.intent.LoginIntents
import com.example.project_miniMart.ui.flows.authFlow.screens.login.model.LoginStates
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent
import com.example.project_miniMart.widgets.loader.DsLoaderView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginModel @Inject constructor(
    private val loginTask: LoginTask,
    private val dataStorePref: DataStorePref,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginStates())
    val state: StateFlow<LoginStates> = _state


    val channel = Channel<LoginIntents>(Channel.BUFFERED)

    init {
        setupIntents()
    }


    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        is LoginIntents.DoLogin -> toDoLogin(
                            _state.value.userName,
                            state.value.password
                        )

                        is LoginIntents.UserNameChangeValue -> onValueUserNameChange(it.dataString)
                        is LoginIntents.PasswordChangeValue -> onValuePasswordChange(it.dataString)
                        is LoginIntents.CheckBoxChecked -> onCheBoxCheckBox(it.isChecked)
                        LoginIntents.HideAlert -> _state.value = _state.value.copy(
                            showAlert = false,
                            errorMassageAlert = 0,
                            typeAlert = null
                        )
                    }
                }
        }
    }

    private fun onCheBoxCheckBox(checked: Boolean) {
        _state.value = _state.value.copy(isCheckBoxChecked = checked)
    }

    private fun onValueUserNameChange(value: String) {
        _state.value = _state.value.copy(
            userName = value,
            isEnableButton = value.isNotEmpty() && _state.value.password.isNotEmpty()
        )
    }

    private fun onValuePasswordChange(value: String) {
        _state.value = _state.value.copy(
            password = value,
            isEnableButton = _state.value.userName.isNotEmpty() && value.isNotEmpty()
        )
    }


    private suspend fun toDoLogin(userName: String, password: String) {
        DsLoaderView.showLoader()
        handleRequest(
            call = { loginTask.fetchLogin(LoginRequest(userName, password)) },
            onSuccess = {
                if(_state.value.isCheckBoxChecked) saveDataUser(it)

                AuthEventManager.triggerEvent(AuthEvent.GoToHome)
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

    private fun saveDataUser(userModel: UserDataDomain){
        CoroutineScope(Dispatchers.IO).launch {
            dataStorePref.saveDataUser(
                email = userModel.email,
                userName = userModel.userName,
            )
        }
    }
}