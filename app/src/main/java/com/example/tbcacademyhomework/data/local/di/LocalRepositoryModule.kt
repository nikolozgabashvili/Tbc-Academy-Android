package com.example.tbcacademyhomework.data.local.di

import com.example.tbcacademyhomework.data.local.repository.MealLocalRepositoryImpl
import com.example.tbcacademyhomework.domain.meal.repository.MealLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsLocalRepository(
        localRepositoryImpl: MealLocalRepositoryImpl
    ): MealLocalDataSource

}
