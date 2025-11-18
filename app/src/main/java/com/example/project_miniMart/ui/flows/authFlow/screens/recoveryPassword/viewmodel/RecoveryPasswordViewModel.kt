package com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.R
import com.example.project_miniMart.domain.repositories.LoginTask
import com.example.project_miniMart.utils.extensions.validEmail
import com.example.project_miniMart.utils.handleRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estado de la pantalla de Recuperación de Contraseña.
 */
data class RecoveryPasswordUiState(
    val email: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val successMessage: Int? = null,
    val errorMessage: Int? = null
)

/**
 * ViewModel para la pantalla de Recuperación de Contraseña.
 *
 * @param loginTask El caso de uso para la recuperación de contraseña.
 */
@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor(private val loginTask: LoginTask) : ViewModel() {

    private val _uiState = MutableStateFlow(RecoveryPasswordUiState())
    val uiState: StateFlow<RecoveryPasswordUiState> = _uiState.asStateFlow()

    /**
     * Actualiza el email en el estado de la UI.
     */
    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                isButtonEnabled = email.validEmail()
            )
        }
    }

    /**
     * Inicia el proceso de recuperación de contraseña.
     */
    fun onRecoverClick() {
        viewModelScope.launch {
            handleRequest(
                onLoading = { _uiState.update { it.copy(isLoading = true) } },
                call = { loginTask.recoveryPassword(_uiState.value.email) },
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, successMessage = R.string.success_recovery) }
                },
                onError = { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error) }
                }
            )
        }
    }

    /**
     * Oculta el mensaje de éxito.
     */
    fun hideSuccessMessage() {
        _uiState.update { it.copy(successMessage = null) }
    }

    /**
     * Oculta el mensaje de error.
     */
    fun hideErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}
