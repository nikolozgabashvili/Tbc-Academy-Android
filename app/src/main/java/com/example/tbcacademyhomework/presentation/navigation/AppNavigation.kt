package com.example.tbcacademyhomework.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tbcacademyhomework.presentation.navigation.NavControllerKey.REGISTER_RESULT_EMAIL
import com.example.tbcacademyhomework.presentation.navigation.NavControllerKey.REGISTER_RESULT_PASSWORD
import com.example.tbcacademyhomework.presentation.screen.login.LoginScreenRoot
import com.example.tbcacademyhomework.presentation.screen.profile.ProfileScreenRoot
import com.example.tbcacademyhomework.presentation.screen.register.RegisterScreenRoot
import com.example.tbcacademyhomework.presentation.screen.users.UsersScreenRoot
import com.example.tbcacademyhomework.presentation.utils.getValue

@Composable
fun AppNavigation(navController: NavHostController, snackBarState: SnackbarHostState) {

    val context = LocalContext.current

    NavHost(navController, startDestination = LoginScreenRoute) {

        composable<LoginScreenRoute> {
            val email = it.savedStateHandle.get<String>(REGISTER_RESULT_EMAIL)
            val password = it.savedStateHandle.get<String>(REGISTER_RESULT_PASSWORD)
            LoginScreenRoot(
                userEmail = email ?: "",
                userPassword = password ?: "",
                showError = {
                    val message = it.getValue(context)
                    message?.let {
                        snackBarState.showSnackbar(message)
                    }
                },
                navigateToRegister = {
                    navController.navigate(RegisterScreenRoute)
                },
                navigateToUsers = {
                    navController.navigate(UsersScreenRoute) {
                        popUpTo(LoginScreenRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }

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
                    val message = it.getValue(context)
                    message?.let {
                        snackBarState.showSnackbar(message)
                    }
                }
            )
        }

        composable<UsersScreenRoute> {
            UsersScreenRoot(
                navigateToProfile = {
                    navController.navigate(ProfileScreenRoute)
                }
            )
        }
        composable<ProfileScreenRoute> {
            ProfileScreenRoot(
                navigateToLogin = {
                    navController.navigate(LoginScreenRoute) {
                        popUpTo<UsersScreenRoute> {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

}


