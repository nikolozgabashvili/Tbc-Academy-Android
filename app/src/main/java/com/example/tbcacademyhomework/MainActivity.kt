package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.tbcacademyhomework.presentation.navigation.AppNavigation
import com.example.tbcacademyhomework.presentation.theme.AppColor
import com.example.tbcacademyhomework.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val snackBarHost = remember { SnackbarHostState() }
            val navController = rememberNavController()
            AppTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackBarHost) },
                    containerColor = AppColor.canvas,
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .consumeWindowInsets(padding)
                            .imePadding(),
                    ) {
                        AppNavigation(navController,snackBarHost)
                    }
                }
            }
        }


    }

}