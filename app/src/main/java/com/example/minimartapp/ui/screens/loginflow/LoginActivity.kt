package com.example.minimartapp.ui.screens.loginflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.minimartapp.ui.screens.homeflow.HomeActivity
import com.example.minimartapp.ui.screens.loginflow.navigation.LoginFlowNavHost
import com.example.minimartapp.ui.theme.MiniMartAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Opcional: cierra LoginActivity para que no vuelva atrás
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniMartAppTheme {

                Scaffold(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        LoginFlowNavHost(
                            onNavigateToHome = { navigateToHome() }
                        )
                    }
                }
            }
        }
    }
}

