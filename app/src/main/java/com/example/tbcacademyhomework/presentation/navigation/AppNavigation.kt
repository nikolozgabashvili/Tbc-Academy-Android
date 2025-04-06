package com.example.tbcacademyhomework.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tbcacademyhomework.presentation.navigation.auth.login.LoginNavGraph
import com.example.tbcacademyhomework.presentation.navigation.auth.login.loginNavigation
import com.example.tbcacademyhomework.presentation.navigation.auth.register.registerNavigation
import com.example.tbcacademyhomework.presentation.navigation.profile.profileNavigation
import com.example.tbcacademyhomework.presentation.navigation.users.usersNavigation

@Composable
fun AppNavigation(navController: NavHostController, snackBarState: SnackbarHostState) {

    NavHost(navController, startDestination = LoginNavGraph) {

        loginNavigation(
            navController = navController,
            snackBarState = snackBarState
        )

        registerNavigation(
            navController = navController,
            snackBarHostState = snackBarState
        )

        usersNavigation()

        profileNavigation(navController)
    }

}


