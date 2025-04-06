package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tbcacademyhomework.presentation.navigation.AppNavigation
import com.example.tbcacademyhomework.presentation.navigation.topLevelRoutes
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
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        AnimatedVisibility(
                            visible = topLevelRoutes.any { it.startDestinationString == navBackStackEntry?.destination?.route.orEmpty() },
                            enter = fadeIn() + expandVertically(),
                            exit = ExitTransition.None
                        ) {
                            NavigationBar {
                                topLevelRoutes.forEach { route ->
                                    NavigationBarItem(
                                        icon = { Icon(route.icon, contentDescription = null) },
                                        selected = currentDestination?.hierarchy?.any { it.route == route.startDestinationString } == true,
                                        onClick = {
                                            navController.navigate(route.route) {
                                                popUpTo(navController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }

                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .consumeWindowInsets(padding)
                            .imePadding(),
                    ) {
                        AppNavigation(navController, snackBarHost)
                    }
                }
            }
        }


    }

}