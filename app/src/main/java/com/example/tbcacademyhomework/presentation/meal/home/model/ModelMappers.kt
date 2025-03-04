package com.example.tbcacademyhomework.presentation.meal.home.model

import com.example.tbcacademyhomework.domain.meal.model.MealCategoryListDomain
import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain
import com.example.tbcacademyhomework.presentation.meal.home.screen.details.model.MealDetailUi

fun MealCategoryListDomain.toUi(): List<CategoryUi> {
    return categories.map {
        CategoryUi(
            id = it.id,
            name = it.categoryName,
        )
    }
}

fun MealDetailUi.toDomain():MealDetailListDomain.MealDetailDomain{
    return MealDetailListDomain.MealDetailDomain(
        id = id,
        name = name,
        category = category,
        area = area,
        instructions = instructions,
        image = image,
        youtubeVideo = youtubeVideo,
        ingredientMap = ingredientMap?: emptyMap()
    )
}