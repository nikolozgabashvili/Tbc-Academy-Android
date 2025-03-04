package com.example.tbcacademyhomework.presentation.meal.favourites

import com.example.tbcacademyhomework.presentation.meal.home.model.MealUi

data class FavouriteScreenState(
    val favourites: List<MealUi> = emptyList()
)
