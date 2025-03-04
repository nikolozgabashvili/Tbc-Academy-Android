package com.example.tbcacademyhomework.presentation.meal.home.model

import com.example.tbcacademyhomework.domain.meal.model.MealCategoryListDomain

fun MealCategoryListDomain.toUi(): List<CategoryUi> {
    return categories.map {
        CategoryUi(
            id = it.id,
            name = it.categoryName,
        )
    }
}