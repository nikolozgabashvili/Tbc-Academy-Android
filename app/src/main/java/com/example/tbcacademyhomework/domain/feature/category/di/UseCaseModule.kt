package com.example.tbcacademyhomework.domain.feature.category.di

import com.example.tbcacademyhomework.domain.feature.category.usecase.GetEquipmentCategoriesUseCase
import com.example.tbcacademyhomework.domain.feature.category.usecase.GetEquipmentCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsGetEquipmentCategoriesUseCase(impl: GetEquipmentCategoriesUseCaseImpl): GetEquipmentCategoriesUseCase
}