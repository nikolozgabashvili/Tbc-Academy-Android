package com.example.tbcacademyhomework.data.category.di

import com.example.tbcacademyhomework.data.category.repository.EquipmentCategoryRepositoryImpl
import com.example.tbcacademyhomework.domain.feature.category.repository.EquipmentCategoryRepository
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
    abstract fun bindEquipmentCategoriesRepository(impl: EquipmentCategoryRepositoryImpl): EquipmentCategoryRepository
}