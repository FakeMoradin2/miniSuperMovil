package com.example.project_miniMart.ui.flows.splash


import androidx.lifecycle.ViewModel
import com.example.project_miniMart.datasource.local.preferences.DataStorePref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashComposeViewModel @Inject constructor(private val dataStorePref: DataStorePref) : ViewModel() {
    val isLogged = dataStorePref.getIsLoggedIn
}