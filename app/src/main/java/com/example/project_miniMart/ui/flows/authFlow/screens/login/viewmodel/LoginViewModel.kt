package com.example.project_miniMart.ui.flows.authFlow.screens.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.local.preferences.DataStorePref
import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.domain.models.UserDataDomain
import com.example.project_miniMart.domain.repositories.LoginTask
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
 * Estado de la pantalla de Login.
 */
data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val isCheckBoxChecked: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null
)

/**
 * ViewModel para la pantalla de Login.
 *
 * @param loginTask El caso de uso para el login.
 * @param dataStorePref El gestor de preferencias para guardar datos de usuario.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginTask: LoginTask,
    private val dataStorePref: DataStorePref,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    /**
     * Actualiza el nombre de usuario en el estado de la UI.
     */
    fun onUserNameChange(userName: String) {
        _uiState.update {
            it.copy(userName = userName, isButtonEnabled = userName.isNotEmpty() && it.password.isNotEmpty())
        }
    }

    /**
     * Actualiza la contraseña en el estado de la UI.
     */
    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(password = password, isButtonEnabled = it.userName.isNotEmpty() && password.isNotEmpty())
        }
    }

    /**
     * Actualiza el estado del checkbox.
     */
    fun onCheckBoxChange(isChecked: Boolean) {
        _uiState.update { it.copy(isCheckBoxChecked = isChecked) }
    }

    /**
     * Realiza el proceso de login.
     */
    fun doLogin() {
        viewModelScope.launch {
            handleRequest(
                onLoading = { _uiState.update { it.copy(isLoading = true) } },
                call = { loginTask.fetchLogin(LoginRequest(_uiState.value.userName, _uiState.value.password)) },
                onSuccess = { user ->
                    _uiState.update { it.copy(isLoading = false) }
                    if (_ui_state.value.isCheckBoxChecked) {
                        saveUserData(user)
                    }
                    AuthEventManager.triggerEvent(AuthEvent.GoToHome)
                },
                onError = { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error) }
                }
            )
        }
    }

    /**
     * Guarda los datos del usuario si el login es exitoso.
     */
    private fun saveUserData(userModel: UserDataDomain) {
        viewModelScope.launch {
            dataStorePref.saveDataUser(
                email = userModel.email,
                userName = userModel.userName,
            )
        }
    }

    /**
     * Oculta el mensaje de error.
     */
    fun hideError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}
