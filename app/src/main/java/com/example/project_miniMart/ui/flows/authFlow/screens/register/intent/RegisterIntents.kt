package com.example.project_miniMart.ui.flows.authFlow.screens.register.intent


sealed class RegisterIntents {
    data object DoRegister : RegisterIntents()
    data class PasswordChangeValue(val dataString: String): RegisterIntents()
    data class NameChangeValue(val dataString: String): RegisterIntents()
    data class EmailChangeValue(val dataString: String): RegisterIntents()
    data class PhoneChangeValue(val dataString: String): RegisterIntents()
    data class ConfirmPasswordChangeValue(val dataString: String): RegisterIntents()
    data class CheckBoxChecked(val isChecked: Boolean): RegisterIntents()
    data object HideAlert: RegisterIntents()
}