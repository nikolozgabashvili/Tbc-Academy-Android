package com.example.tbcacademyhomework.presentation.meal.home.screen.details.model

import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain

fun MealDetailListDomain.MealDetailDomain.toUi(): MealDetailUi {
    return MealDetailUi(
        id = id,
        name = name,
        category = category,
        area = area,
        instructions = instructions,
        image = image,
        youtubeVideo = youtubeVideo,
        ingredientMap = ingredientMap
    )
}