package com.example.project_miniMart.utils.uiManager.events

import androidx.annotation.StringRes


sealed class AuthEvent {
    data class ShowToast(@StringRes val message: Int) : AuthEvent()
    data class NavigateTo(val route: Any) : AuthEvent()
    data object GoToHome : AuthEvent()
}
sealed class HomeEvent {
    data object RefreshApp : HomeEvent()
    data object FinishApp : HomeEvent()
    data class ShowSnackBar(@StringRes val message: Int): HomeEvent()
    data class NavigateTo(val route: Any) : HomeEvent()
}