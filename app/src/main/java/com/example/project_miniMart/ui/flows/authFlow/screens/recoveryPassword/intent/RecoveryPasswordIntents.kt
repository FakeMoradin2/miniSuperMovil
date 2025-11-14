package com.example.project_miniMart.ui.flows.authFlow.screens.recoveryPassword.intent

sealed class RecoveryPasswordIntents {
    data object RecoveryPassword : RecoveryPasswordIntents()
    data class EmailChangeValue(val dataString: String): RecoveryPasswordIntents()
    data object HideAlert: RecoveryPasswordIntents()
}