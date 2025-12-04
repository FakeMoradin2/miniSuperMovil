package com.example.project_miniMart.ui.flows.splash


import com.example.project_miniMart.datasource.local.preferences.DataStorePref

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashComposeViewModel @Inject constructor(private val dataStorePref: DataStorePref) : ViewModel() {
    val isLogged = dataStorePref.getIsLoggedIn
}