package com.example.tbcacademyhomework.data.local.repository

import com.example.tbcacademyhomework.data.local.database.converter.toDomain
import com.example.tbcacademyhomework.data.local.database.converter.toEntity
import com.example.tbcacademyhomework.data.local.database.dao.MealDao
import com.example.tbcacademyhomework.domain.meal.model.MealDetailListDomain
import com.example.tbcacademyhomework.domain.meal.repository.MealLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealLocalRepositoryImpl @Inject constructor(
    private val dao: MealDao
) : MealLocalDataSource {
    override fun getMeals(): Flow<List<MealDetailListDomain.MealDetailDomain>> {
        return dao.getAllMeals().map {
            it.map {
                it.toDomain()
            }
        }
    }

    override  fun isMealFavourite(id: String): Flow<Boolean> {
        return dao.isMealFavourite(id)
    }

    override suspend fun addMealToFavourites(meal: MealDetailListDomain.MealDetailDomain) {
        dao.upsertMeal(meal.toEntity())
    }

    override suspend fun removeMealFromFavourites(mealId: String) {
        dao.deleteMealById(mealId)
    }

    override suspend fun clearFavourites() {
        dao.deleteAllMeals()
    }

    override suspend fun getMealById(id: String): MealDetailListDomain.MealDetailDomain? {
        return dao.getMealById(id)?.toDomain()
    }
}