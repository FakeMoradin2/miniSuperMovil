package com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.model

import com.example.minimartapp.ui.widgets.TypeAlert

data class RecoveryPasswordStates(
    val email:String = "",
    val isErrorEmail: Boolean = false,
    val isEnableButton: Boolean = false,
    val showAlert: Boolean = false,
    val errorMassageAlert: Int = 0,
    val typeAlert: TypeAlert? = null
)
