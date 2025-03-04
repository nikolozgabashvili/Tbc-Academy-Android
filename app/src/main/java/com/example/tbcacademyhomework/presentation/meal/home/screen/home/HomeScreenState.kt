package com.example.tbcacademyhomework.presentation.meal.home.screen.home

import com.example.tbcacademyhomework.presentation.meal.home.model.CategoryUi
import com.example.tbcacademyhomework.presentation.meal.home.model.MealByCategory
import com.example.tbcacademyhomework.presentation.meal.home.model.MealUi

data class HomeScreenState(
    val categoryLoading: Boolean = false,
    val mealLoading: Boolean = false,
    private val rawCategories: List<CategoryUi> = emptyList(),
    val rawMeals: List<MealByCategory> = emptyList(),
    val selectedCategoryId: String? = null
) {
    val mappedCategories: List<CategoryUi>
        get() = rawCategories.map {
            it.copy(isSelected = it.id == selectedCategoryId)
        }

    val mealsToShow: List<MealUi>
        get() = rawMeals.firstOrNull { it.categoryId == selectedCategoryId }?.meals ?: emptyList()


}
