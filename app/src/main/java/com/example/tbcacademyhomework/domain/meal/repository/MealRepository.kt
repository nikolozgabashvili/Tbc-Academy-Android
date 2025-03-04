package com.example.tbcacademyhomework.domain.meal.repository

import com.example.tbcacademyhomework.domain.core.util.DataError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.meal.model.MealCategoryListDomain
import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain
import com.example.tbcacademyhomework.domain.meal.model.MealListDomain
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun getMealCategories(): Flow<Resource<MealCategoryListDomain, DataError>>
    suspend fun getMealsByCategory(category: String): Flow<Resource<MealListDomain, DataError>>
    suspend fun getMealById(id: String): Flow<Resource<MealDetailListDomain.MealDetailDomain, DataError>>
}