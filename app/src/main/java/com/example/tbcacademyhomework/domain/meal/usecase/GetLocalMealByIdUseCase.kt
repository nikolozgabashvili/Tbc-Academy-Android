package com.example.tbcacademyhomework.domain.meal.usecase

import com.example.tbcacademyhomework.domain.meal.repository.MealLocalDataSource
import javax.inject.Inject

class GetLocalMealByIdUseCase @Inject constructor(
    private val repository: MealLocalDataSource
) {
    suspend operator fun invoke(id: String) = repository.getMealById(id)
}