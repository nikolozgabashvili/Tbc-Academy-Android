package com.example.tbcacademyhomework.domain.meal.usecase

import com.example.tbcacademyhomework.domain.meal.repository.MealLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMealFavouriteUseCase @Inject constructor(
    private val mealRepository: MealLocalDataSource
) {
    operator fun invoke(id: String): Flow<Boolean> {
        return mealRepository.isMealFavourite(id)
    }
}