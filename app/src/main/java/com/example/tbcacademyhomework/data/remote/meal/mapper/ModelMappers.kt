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
    val ingredientsMap: MutableMap<String?, String?> = mutableMapOf()

    ingredientsMap[this.strIngredient1] = strMeasure1
    ingredientsMap[this.strIngredient2] = strMeasure2
    ingredientsMap[this.strIngredient3] = strMeasure3
    ingredientsMap[this.strIngredient4] = strMeasure4
    ingredientsMap[this.strIngredient5] = strMeasure5
    ingredientsMap[this.strIngredient6] = strMeasure6
    ingredientsMap[this.strIngredient7] = strMeasure7
    ingredientsMap[this.strIngredient8] = strMeasure8
    ingredientsMap[this.strIngredient9] = strMeasure9
    ingredientsMap[this.strIngredient10] = strMeasure10
    ingredientsMap[this.strIngredient11] = strMeasure11
    ingredientsMap[this.strIngredient12] = strMeasure12
    ingredientsMap[this.strIngredient13] = strMeasure13
    ingredientsMap[this.strIngredient14] = strMeasure14
    ingredientsMap[this.strIngredient15] = strMeasure15
    ingredientsMap[this.strIngredient16] = strMeasure16
    ingredientsMap[this.strIngredient17] = strMeasure17
    ingredientsMap[this.strIngredient18] = strMeasure18
    ingredientsMap[this.strIngredient19] = strMeasure19
    ingredientsMap[this.strIngredient20] = strMeasure20


    return MealDetailListDomain.MealDetailDomain(
        id = idMeal ?: "",
        name = strMeal,
        category = strCategory,
        area = strArea,
        instructions = strInstructions,
        image = strMealThumb,
        youtubeVideo = strYoutube,
        ingredientMap = ingredientsMap
    )
}