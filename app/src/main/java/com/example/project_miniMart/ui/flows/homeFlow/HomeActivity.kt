package com.example.project_miniMart.ui.flows.homeFlow

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
import com.example.project_miniMart.ui.flows.homeFlow.navigation.NavHostHome
import com.example.project_miniMart.ui.theme.NewProjectMVVMTheme
import com.example.project_miniMart.utils.uiManager.AuthEventManager
import com.example.project_miniMart.utils.uiManager.HomeEventManager
import com.example.project_miniMart.utils.uiManager.events.AuthEvent
import com.example.project_miniMart.utils.uiManager.events.HomeEvent
import com.example.project_miniMart.widgets.loader.DsWidgetLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
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
                        NavHostHome(navController)
                    }
                    DsWidgetLoader()
                }

                LaunchedEffect(HomeEventManager) {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        HomeEventManager.eventsFlow.collect {
                            when (it) {
                                HomeEvent.FinishApp -> this@HomeActivity.finish()
                            }
                        }
                    }
                }
            }
        }
    }
}