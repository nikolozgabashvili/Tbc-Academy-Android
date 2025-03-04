package com.example.tbcacademyhomework.data.remote.meal.mapper

import com.example.tbcacademyhomework.data.remote.meal.model.MealCategoryListDTO
import com.example.tbcacademyhomework.data.remote.meal.model.MealDetailListDTO
import com.example.tbcacademyhomework.data.remote.meal.model.MealListDTO
import com.example.tbcacademyhomework.domain.meal.model.MealCategoryListDomain
import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain
import com.example.tbcacademyhomework.domain.meal.model.MealListDomain

fun MealCategoryListDTO.toDomain(): MealCategoryListDomain {
    return MealCategoryListDomain(
        categories = categories.map { it.toDomain() }
    )
}

private fun MealCategoryListDTO.MealCategoryDTO.toDomain(): MealCategoryListDomain.CategoryDomain {
    return MealCategoryListDomain.CategoryDomain(
        id = idCategory,
        categoryName = strCategory
    )
}

fun MealListDTO.toDomain(): MealListDomain {
    return MealListDomain(
        meals = meals.map { it.toDomain() }
    )
}

private fun MealListDTO.MealDTO.toDomain(): MealListDomain.MealDomain {
    return MealListDomain.MealDomain(
        id = idMeal,
        mealImage = strMealThumb,
        mealName = strMeal
    )
}

fun MealDetailListDTO.toDomain(): MealDetailListDomain {
    return MealDetailListDomain(
        meals = meals.map { it.toDomain() }
    )
}

fun MealDetailListDTO.MealDetailDTO.toDomain(): MealDetailListDomain.MealDetailDomain {
    val ingredientsMap: MutableMap<String?, String> = mutableMapOf()
    for (i in 1..20) {
        val ingredientField = this::class.java.getDeclaredField("strIngredient$i").get(this) as? String
        val measureField = this::class.java.getDeclaredField("strMeasure$i").get(this) as? String
        if (!ingredientField.isNullOrEmpty() && !measureField.isNullOrEmpty()) {
            ingredientsMap[ingredientField] = measureField
        }
    }

    return MealDetailListDomain.MealDetailDomain(
        id = idMeal,
        name = strMeal,
        category = strCategory,
        area = strArea,
        instructions = strInstructions,
        image = strMealThumb,
        youtubeVideo = strYoutube,
        ingredientMap = ingredientsMap
    )
}