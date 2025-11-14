package com.example.project_miniMart.ui.flows.authFlow.screens.login.model

import com.example.minimartapp.ui.widgets.TypeAlert

data class LoginStates(
    val userName:String = "",
    val password: String= "",

    val isErrorUserName: Boolean = false,

    val isErrorPassword: Boolean = false,

    val isEnableButton: Boolean = false,

    val isCheckBoxChecked: Boolean = false,

    val showAlert: Boolean = false,
    val errorMassageAlert: Int = 0,
    val typeAlert: TypeAlert? = null
)
