package com.example.project_miniMart.ui.flows.authFlow.screens.login.intent

sealed class LoginIntents {
    data object DoLogin : LoginIntents()
    data class UserNameChangeValue(val dataString: String): LoginIntents()
    data class PasswordChangeValue(val dataString: String): LoginIntents()
    data class CheckBoxChecked(val isChecked: Boolean): LoginIntents()
    data object HideAlert: LoginIntents()
}