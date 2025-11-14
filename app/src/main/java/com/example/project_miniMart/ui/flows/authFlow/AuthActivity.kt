package com.example.project_miniMart.ui.flows.authFlow

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project_miniMart.ui.flows.authFlow.navigation.NavHostLogin
import com.example.project_miniMart.ui.flows.homeFlow.HomeActivity
import com.example.project_miniMart.ui.theme.NewProjectMVVMTheme
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent
import com.example.project_miniMart.widgets.loader.DsWidgetLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            NewProjectMVVMTheme {
                val navController: NavHostController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(it)
                            .background(Color.White)
                            .fillMaxSize()
                    ) {
                        NavHostLogin(navController)
                    }
                    DsWidgetLoader()
                }

                LaunchedEffect(AuthEventManager) {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        AuthEventManager.eventsFlow.collect {
                            when (it) {
                                is AuthEvent.ShowToast -> {
                                    Toast.makeText(
                                        this@AuthActivity,
                                        it.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                AuthEvent.GoToHome -> {
                                    val intent = Intent(this@AuthActivity, HomeActivity::class.java)
                                    startActivity(intent)
                                    this@AuthActivity.finish()
                                }

                                is AuthEvent.NavigateTo -> navController.navigate(
                                    it.route
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}