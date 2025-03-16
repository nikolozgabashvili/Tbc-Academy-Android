package com.example.tbcacademyhomework.data.core.di

import com.example.tbcacademyhomework.domain.common.usecase.ClearDataUseCase
import com.example.tbcacademyhomework.domain.common.usecase.ClearDataUseCaseImpl
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
    abstract fun bindClearDataUseCase(
        clearDataUseCaseImpl: ClearDataUseCaseImpl
    ): ClearDataUseCase
}