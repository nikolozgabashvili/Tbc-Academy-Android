package com.example.tbcacademyhomework.data.remote.meal.di

import com.example.tbcacademyhomework.data.remote.meal.repository.MealRepositoryImpl
import com.example.tbcacademyhomework.domain.meal.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMealRepository(
        foodRepositoryImpl: MealRepositoryImpl
    ): MealRepository
}