package com.example.tbcacademyhomework.domain.meal.model

data class MealCategoryListDomain(
    val categories: List<CategoryDomain>
) {
    data class CategoryDomain(
        val id: String,
        val categoryName: String,
    )

}

