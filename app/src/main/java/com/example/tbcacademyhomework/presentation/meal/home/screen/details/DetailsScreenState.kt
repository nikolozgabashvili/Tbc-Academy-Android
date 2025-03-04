package com.example.tbcacademyhomework.presentation.meal.home.screen.details

import com.example.tbcacademyhomework.presentation.meal.home.screen.details.model.MealDetailUi

data class DetailsScreenState(
    val loading: Boolean = false,
    val details: MealDetailUi? = null,
    val hasInternetConnection: Boolean = true,
    val isError: Boolean = false
)