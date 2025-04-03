package com.example.tbcacademyhomework.presentation.navigation

import androidx.navigation.NavHostController

fun NavHostController.setScreenResult(
    key: String,
    value: Any
) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)

}