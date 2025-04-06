package com.example.tbcacademyhomework.presentation.navigation.auth.register

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tbcacademyhomework.presentation.navigation.NavControllerKeys.REGISTER_RESULT_EMAIL
import com.example.tbcacademyhomework.presentation.navigation.NavControllerKeys.REGISTER_RESULT_PASSWORD
import com.example.tbcacademyhomework.presentation.navigation.setScreenResult
import com.example.tbcacademyhomework.presentation.screen.register.RegisterScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.registerNavigation(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState
) {
    navigation<RegisterNavGraph>(startDestination = RegisterScreenRoute){

        composable<RegisterScreenRoute> {
            RegisterScreenRoot(
                navigateBack = {
                    it?.let {
                        navController.setScreenResult(REGISTER_RESULT_EMAIL, it.email)
                        navController.setScreenResult(REGISTER_RESULT_PASSWORD, it.password)
                    }
                    navController.navigateUp()
                },
                showError = {
                    snackBarHostState.showSnackbar(it)
                }
            )
        }
    }
}

@Serializable
data object RegisterScreenRoute

@Serializable
data object RegisterNavGraph