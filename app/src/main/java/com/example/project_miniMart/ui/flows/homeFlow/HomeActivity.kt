package com.example.project_miniMart.ui.flows.homeFlow

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationHistory
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationSaleDetail
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationShoppingCar
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationVoucher
import com.example.project_miniMart.ui.flows.homeFlow.navigation.NavHostHome
import com.example.project_miniMart.ui.theme.NewProjectMVVMTheme
import com.example.project_miniMart.utils.uiManager.HomeEventManager
import com.example.project_miniMart.utils.uiManager.events.HomeEvent
import com.example.project_miniMart.widgets.loader.DsWidgetLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            val scope = rememberCoroutineScope()
            val snackBarHostState = remember { SnackbarHostState() }

            NewProjectMVVMTheme {
                val navController: NavHostController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
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
                                is HomeEvent.ShowSnackBar -> {
                                    scope.launch {
                                        snackBarHostState.showSnackbar(getString(it.message))
                                    }
                                }

                                is HomeEvent.NavigateTo -> {
                                    when (val route = it.route) {
                                        is DestinationShoppingCar, DestinationHistory, is DestinationSaleDetail ->  {
                                            navController.navigate(route)
                                        }

                                        is DestinationVoucher -> {
                                            navController.navigate(route) {
                                                popUpTo(0) { inclusive = true }
                                                launchSingleTop = true
                                            }
                                        }
                                    }
                                }

                                HomeEvent.RefreshApp -> {
                                    val intent = intent
                                    finish()
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}