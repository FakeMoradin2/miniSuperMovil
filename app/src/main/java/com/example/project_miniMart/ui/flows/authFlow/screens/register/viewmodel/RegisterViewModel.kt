package com.example.project_miniMart.ui.flows.authFlow.screens.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.datasource.network.requests.RegisterRequest
import com.example.project_miniMart.domain.repositories.LoginTask
import com.example.project_miniMart.utils.extensions.validEmail
import com.example.project_miniMart.utils.extensions.validateLength
import com.example.project_miniMart.utils.extensions.validatePhone
import com.example.project_miniMart.utils.extensions.validateSpecialCharacter
import com.example.project_miniMart.utils.extensions.validateThreeNumbersInSequence
import com.example.project_miniMart.utils.extensions.validateUppercase
import com.example.project_miniMart.utils.handleRequest
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estado de la pantalla de Registro.
 */
data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isTermsChecked: Boolean = false,
    val isEmailValid: Boolean = true,
    val isPhoneValid: Boolean = true,
    val isPasswordLengthValid: Boolean = false,
    val isPasswordUpperCaseValid: Boolean = false,
    val isPasswordSpecialCharValid: Boolean = false,
    val isPasswordConsecutiveNumValid: Boolean = false,
    val arePasswordsMatching: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null
)

/**
 * ViewModel para la pantalla de Registro.
 *
 * @param loginTask El caso de uso para el registro.
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(private val loginTask: LoginTask) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
        validateForm()
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, isEmailValid = email.validEmail()) }
        validateForm()
    }

    fun onPhoneChange(phone: String) {
        _uiState.update { it.copy(phoneNumber = phone, isPhoneValid = phone.validatePhone()) }
        validateForm()
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                isPasswordLengthValid = password.validateLength(8),
                isPasswordUpperCaseValid = password.validateUppercase(),
                isPasswordSpecialCharValid = password.validateSpecialCharacter(),
                isPasswordConsecutiveNumValid = password.validateThreeNumbersInSequence(),
                arePasswordsMatching = password == it.confirmPassword
            )
        }
        validateForm()
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                arePasswordsMatching = it.password == confirmPassword
            )
        }
        validateForm()
    }

    fun onTermsCheckedChange(isChecked: Boolean) {
        _uiState.update { it.copy(isTermsChecked = isChecked) }
        validateForm()
    }

    private fun validateForm() {
        _uiState.update {
            val isEnabled = with(it) {
                name.isNotEmpty() &&
                        isEmailValid &&
                        isPhoneValid &&
                        isPasswordLengthValid &&
                        isPasswordUpperCaseValid &&
                        isPasswordSpecialCharValid &&
                        isPasswordConsecutiveNumValid &&
                        arePasswordsMatching &&
                        isTermsChecked
            }
            it.copy(isButtonEnabled = isEnabled)
        }
    }

    fun onRegisterClick() {
        viewModelScope.launch {
            val request = with(_uiState.value) {
                RegisterRequest(
                    userName = name,
                    workstation = "Client", // O cualquier otro valor por defecto
                    email = email,
                    age = 18, // O un valor recogido de la UI
                    isPrincipal = false,
                    phone = phoneNumber,
                    password = password // La request necesita el password
                )
            }

            handleRequest(
                onLoading = { _uiState.update { it.copy(isLoading = true) } },
                call = { loginTask.fetchRegister(request) },
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false) }
                    AuthEventManager.triggerEvent(AuthEvent.GoToHome)
                },
                onError = { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error) }
                }
            )
        }
    }

    fun hideError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}