package com.example.tbcacademyhomework.presentation.meal.home.screen.details.model

data class MealDetailUi(
    val id: String?,
    val name: String?,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val image: String?,
    val youtubeVideo: String?,
    val ingredientMap: Map<String?, String?>
)
