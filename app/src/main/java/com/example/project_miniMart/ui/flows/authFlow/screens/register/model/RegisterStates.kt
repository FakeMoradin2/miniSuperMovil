package com.example.project_miniMart.ui.flows.authFlow.screens.register.model

import com.example.minimartapp.ui.widgets.TypeAlert

data class RegisterStates(
    val isLoading: Boolean = false,
    //----
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    //-----
    //val isValidPhone: Boolean = false,

    val isValidPasswordLength: Boolean = false,
    val isValidPasswordUpperCase: Boolean = false,
    val isValidPasswordSpecialCharacter: Boolean = false,
    val isValidPasswordThreeConsecutive: Boolean = false,

    val isErrorEmail: Boolean = false,
    val isErrorPhone: Boolean = false,


    val passWordIsEqual: Boolean = false,

    val isCheckBoxChecked: Boolean = false,

    val isEnableButton: Boolean = false,

    val showAlert: Boolean = false,
    val errorMassageAlert: Int = 0,

    val typeAlert: TypeAlert? = null
)