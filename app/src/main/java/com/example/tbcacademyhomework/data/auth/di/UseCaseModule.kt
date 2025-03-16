package com.example.tbcacademyhomework.data.auth.di

import com.example.tbcacademyhomework.domain.auth.usecase.LoginUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.LoginUseCaseImpl
import com.example.tbcacademyhomework.domain.auth.usecase.RegisterUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.RegisterUseCaseImpl
import com.example.tbcacademyhomework.domain.auth.usecase.ValidateEmailUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidateEmailUseCaseImpl
import com.example.tbcacademyhomework.domain.auth.usecase.ValidatePasswordUseCase
import com.example.tbcacademyhomework.domain.auth.usecase.ValidatePasswordUseCaseImpl
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
    abstract fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindMailValidatorUseCase(impl: ValidateEmailUseCaseImpl): ValidateEmailUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindPasswordValidatorUseCase(impl: ValidatePasswordUseCaseImpl): ValidatePasswordUseCase


}