package com.example.minimartapp.ui.screens.loginflow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimartapp.datasource.comnom.ResponseStatus
import com.example.minimartapp.datasource.requests.LoginRequest
import com.example.minimartapp.domain.models.UserDataDomain
import com.example.minimartapp.domain.usescases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _observerState =
        MutableStateFlow<ResponseStatus<UserDataDomain>>(ResponseStatus.Loading())
    val observerState = _observerState.asStateFlow()

    var nameLoginInput by mutableStateOf("")
    var passwordLoginInput by mutableStateOf("")
    var checkBoxIsCheck by mutableStateOf(false)

    fun fetchLogin(){
       val loginRequest = LoginRequest(
           user = nameLoginInput,
           password = passwordLoginInput
       )
        _observerState.value = ResponseStatus.Loading()
        viewModelScope.launch {
           _observerState.value = loginUseCase(loginRequest)
        }
    }



}
