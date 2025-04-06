package com.example.tbcacademyhomework.presentation.navigation.auth.login

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tbcacademyhomework.presentation.navigation.NavControllerKeys.REGISTER_RESULT_EMAIL
import com.example.tbcacademyhomework.presentation.navigation.NavControllerKeys.REGISTER_RESULT_PASSWORD
import com.example.tbcacademyhomework.presentation.navigation.auth.register.RegisterNavGraph
import com.example.tbcacademyhomework.presentation.navigation.users.UsersNavGraph
import com.example.tbcacademyhomework.presentation.screen.login.LoginScreenRoot
import kotlinx.serialization.Serializable


fun NavGraphBuilder.loginNavigation(
    navController: NavHostController,
    snackBarState: SnackbarHostState
) {

    navigation<LoginNavGraph>(startDestination = LoginScreenRoute) {

        composable<LoginScreenRoute> {
            val email = it.savedStateHandle.get<String>(REGISTER_RESULT_EMAIL)
            val password = it.savedStateHandle.get<String>(REGISTER_RESULT_PASSWORD)
            LoginScreenRoot(
                userEmail = email ?: "",
                userPassword = password ?: "",
                showError = {
                    snackBarState.showSnackbar(it)
                },
                navigateToRegister = {
                    navController.navigate(RegisterNavGraph)
                },
                navigateToUsers = {
                    navController.navigate(UsersNavGraph) {
                        popUpTo(LoginNavGraph) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

}

@Serializable
data object LoginScreenRoute

@Serializable
data object LoginNavGraph