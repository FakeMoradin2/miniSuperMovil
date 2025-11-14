package com.example.project_miniMart.ui.flows.homeFlow.screens.home.intent

sealed class HomeIntents {
    data object GetUserInfo : HomeIntents()
    data object HideAlert : HomeIntents()
    data object GetAllProducts : HomeIntents()
    data class CheckCategory(val label: String) : HomeIntents()
    data class ShowAndHideButtonSheet(val show: Boolean) : HomeIntents()
    data class UpdateGridScrolling(val isScrolling: Boolean) : HomeIntents()
}