package com.example.tbcacademyhomework.data.datastore.di

import com.example.tbcacademyhomework.domain.datastore.usecase.GetValueUseCase
import com.example.tbcacademyhomework.domain.datastore.usecase.GetValueUseCaseImpl
import com.example.tbcacademyhomework.domain.datastore.usecase.RemoveByKeyUseCase
import com.example.tbcacademyhomework.domain.datastore.usecase.RemoveByKeyUseCaseImpl
import com.example.tbcacademyhomework.domain.datastore.usecase.SetValueUseCase
import com.example.tbcacademyhomework.domain.datastore.usecase.SetValueUseCaseImpl
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
    abstract fun bindsGetValueUseCase(impl: GetValueUseCaseImpl): GetValueUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindsSetValueUseCase(impl: SetValueUseCaseImpl): SetValueUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsRemoveByKeyUseCase(impl: RemoveByKeyUseCaseImpl): RemoveByKeyUseCase


}