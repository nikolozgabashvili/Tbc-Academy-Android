package com.example.tbcacademyhomework.presentation.meal.home.screen.details

import com.example.tbcacademyhomework.presentation.meal.home.screen.details.model.MealDetailUi

data class DetailsScreenState(
    val loading: Boolean = false,
    val details: MealDetailUi? = null,
    val isFavourite: Boolean = false,
    val isError: Boolean = false
)