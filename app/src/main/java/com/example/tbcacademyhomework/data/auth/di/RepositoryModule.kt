package com.example.tbcacademyhomework.data.auth.di

import com.example.tbcacademyhomework.data.auth.repository.LoginRepositoryImpl
import com.example.tbcacademyhomework.data.auth.repository.RegisterRepositoryImpl
import com.example.tbcacademyhomework.data.auth.validation.UserDataValidatorImpl
import com.example.tbcacademyhomework.domain.auth.repository.LoginRepository
import com.example.tbcacademyhomework.domain.auth.repository.RegisterRepository
import com.example.tbcacademyhomework.domain.auth.validation.UserDataValidator
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
    abstract fun bindsLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindsRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository

    @Binds
    @Singleton
    abstract fun bindsUserDataValidator(impl: UserDataValidatorImpl): UserDataValidator

}