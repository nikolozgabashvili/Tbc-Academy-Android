package com.example.tbcacademyhomework.presentation.navigation.users

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tbcacademyhomework.presentation.screen.users.UsersScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.usersNavigation() {
    navigation<UsersNavGraph>(startDestination =UsersScreenRoute ){

        composable<UsersScreenRoute> {
            UsersScreenRoot()
        }
    }
}

@Serializable
data object UsersScreenRoute

@Serializable
data object UsersNavGraph