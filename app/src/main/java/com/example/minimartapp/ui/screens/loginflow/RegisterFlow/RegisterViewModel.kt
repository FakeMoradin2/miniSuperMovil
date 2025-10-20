package com.example.minimartapp.ui.screens.loginflow.RegisterFlow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel(){

    var nameRegisterInput by mutableStateOf("")
    var phoneRegisterInput by mutableStateOf("")
    var passwordRegisterInput by mutableStateOf("")
    var confirmRegisterInput by mutableStateOf("")
    var checkBoxIsCheck by mutableStateOf(false)

}