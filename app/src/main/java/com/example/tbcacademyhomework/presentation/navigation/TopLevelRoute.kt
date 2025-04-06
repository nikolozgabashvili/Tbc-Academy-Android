package com.example.tbcacademyhomework.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.presentation.navigation.profile.ProfileNavGraph
import com.example.tbcacademyhomework.presentation.navigation.profile.ProfileScreenRoute
import com.example.tbcacademyhomework.presentation.navigation.users.UsersNavGraph
import com.example.tbcacademyhomework.presentation.navigation.users.UsersScreenRoute

data class TopLevelRoute<T>(
    val nameRes: Int,
    val route: T,
    val startDestinationString: String,
    val icon: ImageVector
)

val topLevelRoutes = listOf(
    TopLevelRoute(
        nameRes = R.string.users,
        route = UsersNavGraph,
        startDestinationString = UsersScreenRoute::class.qualifiedName.orEmpty(),
        icon = Icons.Rounded.Dashboard,
    ),
    TopLevelRoute(
        nameRes = R.string.profile,
        route = ProfileNavGraph,
        startDestinationString = ProfileScreenRoute::class.qualifiedName.orEmpty(),
        icon = Icons.Rounded.Person
    )
)
