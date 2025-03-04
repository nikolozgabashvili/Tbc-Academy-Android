package com.example.tbcacademyhomework.domain.meal.repository

import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain
import kotlinx.coroutines.flow.Flow

interface MealLocalDataSource {
    fun getMeals(): Flow<List<MealDetailListDomain.MealDetailDomain>>
    fun isMealFavourite(id: String): Flow<Boolean>
    suspend fun addMealToFavourites(meal: MealDetailListDomain.MealDetailDomain)
    suspend fun removeMealFromFavourites(mealId: String)
    suspend fun clearFavourites()
    suspend fun getMealById(id: String): MealDetailListDomain.MealDetailDomain?
}