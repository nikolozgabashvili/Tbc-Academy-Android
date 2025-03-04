package com.example.tbcacademyhomework.data.remote.meal.model

import kotlinx.serialization.Serializable

@Serializable
data class MealCategoryListDTO(
    val categories: List<MealCategoryDTO>
) {
    @Serializable
    data class MealCategoryDTO(
        val idCategory: String,
        val strCategory: String,
    )
}