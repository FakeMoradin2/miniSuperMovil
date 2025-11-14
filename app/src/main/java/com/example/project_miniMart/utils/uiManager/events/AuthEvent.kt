package com.example.project_miniMart.utils.uiManager.events

import androidx.annotation.StringRes
import com.example.project_miniMart.domain.models.UserDataDomain


sealed class AuthEvent {
    data class ShowToast(@StringRes val message: Int) : AuthEvent()
    data class NavigateTo(val route: Any) : AuthEvent()
    data object GoToHome : AuthEvent()
}
sealed class HomeEvent {
    data object FinishApp : HomeEvent()
}