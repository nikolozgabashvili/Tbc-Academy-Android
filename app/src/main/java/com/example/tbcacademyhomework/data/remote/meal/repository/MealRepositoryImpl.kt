package com.example.tbcacademyhomework.data.remote.meal.repository

import com.example.tbcacademyhomework.data.remote.core.util.HttpRequestHelper
import com.example.tbcacademyhomework.data.remote.meal.mapper.toDomain
import com.example.tbcacademyhomework.data.remote.meal.service.MealApiService
import com.example.tbcacademyhomework.domain.core.util.DataError
import com.example.tbcacademyhomework.domain.core.util.Resource
import com.example.tbcacademyhomework.domain.core.util.map
import com.example.tbcacademyhomework.domain.meal.model.MealCategoryListDomain
import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain
import com.example.tbcacademyhomework.domain.meal.model.MealListDomain
import com.example.tbcacademyhomework.domain.meal.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApiService,
    private val httpRequestHelper: HttpRequestHelper
) : MealRepository {
    override suspend fun getMealCategories(): Flow<Resource<MealCategoryListDomain, DataError>> {
        return httpRequestHelper.safeCall {
            mealApi.getCategories()
        }.map { resource ->
            resource.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getMealsByCategory(category: String): Flow<Resource<MealListDomain, DataError>> {
        return httpRequestHelper.safeCall {
            mealApi.getMealsByCategory(category)
        }.map { resource ->
            resource.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getMealById(id: String): Flow<Resource<MealDetailListDomain.MealDetailDomain, DataError>> {
        return httpRequestHelper.safeCall {
            mealApi.getMealDetail(id)
        }.map { resource ->
            resource.map {
                it.toDomain().meals.first()
            }
        }
    }
}