package com.example.tbcacademyhomework.presentation.meal.favourites.detail

import com.example.tbcacademyhomework.presentation.meal.home.screen.details.model.MealDetailUi

data class FavouriteDetailsScreenState(
    val details: MealDetailUi? = null,
    val isFavourite: Boolean = false,
)
