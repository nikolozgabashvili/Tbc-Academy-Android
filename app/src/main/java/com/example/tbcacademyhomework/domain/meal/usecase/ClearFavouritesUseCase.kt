package com.example.tbcacademyhomework.domain.meal.usecase

import com.example.tbcacademyhomework.domain.meal.repository.MealLocalDataSource
import javax.inject.Inject

class ClearFavouritesUseCase @Inject constructor(
    private val repository: MealLocalDataSource
) {
    suspend operator fun invoke() {
        repository.clearFavourites()
    }
}