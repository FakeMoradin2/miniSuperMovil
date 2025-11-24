package com.example.project_miniMart.ui.flows.splash


import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project_miniMart.ui.flows.authFlow.AuthActivity
import com.example.project_miniMart.ui.flows.homeFlow.HomeActivity
import com.example.project_miniMart.ui.theme.NewProjectMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashComposeActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            NewProjectMVVMTheme {
                Scaffold { paddingValues ->
                    Box (
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val sharePreferenceViewModel: SplashComposeViewModel = hiltViewModel()
                        val isLogged = sharePreferenceViewModel.isLogged.collectAsState(initial = null)

                        LaunchedEffect(isLogged.value) {
                            when (isLogged.value) {
                                true -> {
                                    startActivity(Intent(this@SplashComposeActivity, HomeActivity::class.java))
                                    this@SplashComposeActivity.finish()
                                }
                                false -> {
                                    startActivity(Intent(this@SplashComposeActivity, AuthActivity::class.java))
                                    this@SplashComposeActivity.finish()
                                }
                                null -> Unit
                            }
                        }
                    }
                }
            }
        }
    }
}