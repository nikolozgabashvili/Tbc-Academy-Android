package com.example.tbcacademyhomework.data.remote.meal.model

import kotlinx.serialization.Serializable

@Serializable
data class MealListDTO(
    val meals: List<MealDTO>
) {
    @Serializable
    data class MealDTO(
        val idMeal: String,
        val strMeal: String,
        val strMealThumb: String
    )
}