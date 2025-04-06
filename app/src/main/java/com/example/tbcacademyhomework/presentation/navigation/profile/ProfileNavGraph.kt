package com.example.tbcacademyhomework.presentation.navigation.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tbcacademyhomework.presentation.navigation.auth.login.LoginNavGraph
import com.example.tbcacademyhomework.presentation.screen.profile.ProfileScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.profileNavigation(
    navController: NavHostController
) {
    navigation<ProfileNavGraph>(startDestination = ProfileScreenRoute) {
        composable<ProfileScreenRoute> {
            ProfileScreenRoot(
                navigateToLogin = {
                    navController.navigate(LoginNavGraph) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }


}

@Serializable
data object ProfileScreenRoute

@Serializable
data object ProfileNavGraph
