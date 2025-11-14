package com.example.project_miniMart.ui.flows.authFlow.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.project_miniMart.datasource.network.requests.RegisterRequest
import com.example.project_miniMart.domain.repositories.LoginTask
import com.example.project_miniMart.ui.flows.authFlow.screens.register.intent.RegisterIntents
import com.example.project_miniMart.ui.flows.authFlow.screens.register.model.RegisterStates
import com.example.project_miniMart.utils.extensions.validEmail
import com.example.project_miniMart.utils.extensions.validateLength
import com.example.project_miniMart.utils.extensions.validatePhone
import com.example.project_miniMart.utils.extensions.validateSpecialCharacter
import com.example.project_miniMart.utils.extensions.validateThreeNumbersInSequence
import com.example.project_miniMart.utils.extensions.validateUppercase
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent
import com.example.project_miniMart.widgets.loader.DsLoaderView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class RegisterViewModel @Inject constructor(private val loginTask: LoginTask) : ViewModel() {
    private val _state = MutableStateFlow(RegisterStates())
    val state: StateFlow<RegisterStates> = _state

    val channel = Channel<RegisterIntents>(Channel.BUFFERED)

    init {
        setupIntents()
    }

    private fun setupIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect {
                    when (it) {
                        RegisterIntents.DoRegister -> toDoRegister()
                        is RegisterIntents.PasswordChangeValue -> onValuePasswordChange(it.dataString)
                        is RegisterIntents.NameChangeValue -> onValueNameChange(it.dataString)
                        is RegisterIntents.EmailChangeValue -> onValueEmailChange(it.dataString)
                        is RegisterIntents.PhoneChangeValue -> onValuePhoneChange(it.dataString)
                        is RegisterIntents.ConfirmPasswordChangeValue -> onValueVerifyPasswordChange(
                            it.dataString
                        )

                        is RegisterIntents.CheckBoxChecked -> onCheBoxCheckBox(it.isChecked)
                        RegisterIntents.HideAlert -> _state.value = _state.value.copy(
                            showAlert = false,
                            errorMassageAlert = 0,
                            typeAlert = null
                        )
                    }
                }
        }
    }


    private fun onCheBoxCheckBox(checked: Boolean) {
        _state.value =
            _state.value.copy(
                isCheckBoxChecked = checked,
                isEnableButton = getIsEnableButton(checked = checked)
            )
    }


    private fun onValueEmailChange(value: String) {
        val updatedState = _state.value.copy(
            email = value,
            isErrorEmail = !value.validEmail()
        )

        _state.value = updatedState.copy(
            isEnableButton = getIsEnableButton(updateState = updatedState)
        )
    }

    private fun onValueVerifyPasswordChange(value: String) {
        val updatedState = _state.value.copy(
            confirmPassword = value,
            passWordIsEqual = value == _state.value.password,
        )

        _state.value = updatedState.copy(
            isEnableButton = getIsEnableButton(updateState = updatedState)
        )
    }

    private fun onValuePasswordChange(value: String) {
        val updateState = _state.value.copy(
            password = value,
            passWordIsEqual = (value == _state.value.confirmPassword) && _state.value.confirmPassword.isNotEmpty(),
            isValidPasswordLength = value.validateLength(8),
            isValidPasswordUpperCase = value.validateUppercase(),
            isValidPasswordSpecialCharacter = value.validateSpecialCharacter(),
            isValidPasswordThreeConsecutive = value.validateThreeNumbersInSequence()
        )

        _state.value = updateState.copy(
            isEnableButton = getIsEnableButton(updateState = updateState)
        )
    }

    private fun onValueNameChange(value: String) {
        val updateState = _state.value.copy(
            name = value
        )

        _state.value = updateState.copy(
            isEnableButton = getIsEnableButton(updateState = updateState)
        )
    }

    private fun onValuePhoneChange(value: String) {
        val updateState = _state.value.copy(
            phoneNumber = value,
            isErrorPhone = !value.validatePhone()
        )

        _state.value = updateState.copy(
            isEnableButton = getIsEnableButton(updateState = updateState)
        )
    }


    private fun getIsEnableButton(
        checked: Boolean = _state.value.isCheckBoxChecked,
        updateState: RegisterStates = _state.value
    ): Boolean {
        return with(updateState) {
            name.isNotEmpty()
                    && checked
                    && phoneNumber.validatePhone()
                    && email.validEmail()
                    && isValidPasswordLength
                    && isValidPasswordUpperCase
                    && isValidPasswordSpecialCharacter
                    && isValidPasswordThreeConsecutive
                    && (passWordIsEqual && confirmPassword.isNotEmpty())
        }
    }

    private suspend fun toDoRegister() {
        val request = with(_state.value) {
            RegisterRequest(
                userName = name,
                workstation = "Client",
                email = email,
                age = 18,
                isPrincipal = false,
                phone = phoneNumber,
            )
        }

        DsLoaderView.showLoader()
        handleRequest(
            call = { loginTask.fetchRegister(request) },
            onSuccess = {
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
}