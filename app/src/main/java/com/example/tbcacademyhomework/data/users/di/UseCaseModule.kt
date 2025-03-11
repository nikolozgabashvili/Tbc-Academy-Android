package com.example.tbcacademyhomework.data.users.di

import com.example.tbcacademyhomework.data.users.repository.GetPagedUsersUseCase
import com.example.tbcacademyhomework.data.users.repository.GetPagedUsersUseCaseImpl
import com.example.tbcacademyhomework.domain.users.usecase.ClearLocalDataUseCase
import com.example.tbcacademyhomework.domain.users.usecase.ClearLocalDataUseCaseImpl
import com.example.tbcacademyhomework.domain.users.usecase.GetLocalUsersUseCase
import com.example.tbcacademyhomework.domain.users.usecase.GetLocalUsersUseCaseImpl
import com.example.tbcacademyhomework.domain.users.usecase.GetRemoteUsersUseCase
import com.example.tbcacademyhomework.domain.users.usecase.GetRemoteUsersUseCaseImpl
import com.example.tbcacademyhomework.domain.users.usecase.UpdateLocalUsersUseCase
import com.example.tbcacademyhomework.domain.users.usecase.UpdateLocalUsersUseCaseImpl
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
    abstract fun bindsGetPagedUsersUseCase(impl: GetPagedUsersUseCaseImpl): GetPagedUsersUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsGetLocalUsersUseCase(impl: GetLocalUsersUseCaseImpl): GetLocalUsersUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsClearLocalDataUseCase(impl: ClearLocalDataUseCaseImpl): ClearLocalDataUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsUpdateUsersUseCase(impl: UpdateLocalUsersUseCaseImpl): UpdateLocalUsersUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindsGetRemoteUsersUseCase(impl: GetRemoteUsersUseCaseImpl): GetRemoteUsersUseCase

}