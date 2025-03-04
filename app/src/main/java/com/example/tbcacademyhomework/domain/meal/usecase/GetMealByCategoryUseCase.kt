package com.example.tbcacademyhomework.domain.meal.usecase

import com.example.tbcacademyhomework.domain.core.util.DataError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.meal.model.MealListDomain
import com.example.tbcacademyhomework.domain.meal.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMealByCategoryUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(category:String): Flow<Resource<MealListDomain, DataError>> {
        return withContext(Dispatchers.IO) {
            repository.getMealsByCategory(category)
        }
    }
}