package com.example.minimartapp.ui.screens.loginflow.PasswordFlow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecoverPasswordViewModel @Inject constructor(): ViewModel() {
    var passwordInput by mutableStateOf("")
}