package com.example.tbcacademyhomework.data.auth.di

import com.example.tbcacademyhomework.data.auth.repository.AuthRepositoryImpl
import com.example.tbcacademyhomework.data.auth.service.AuthApiService
import com.example.tbcacademyhomework.data.auth.validation.UserDataValidatorImpl
import com.example.tbcacademyhomework.domain.auth.repository.AuthRepository
import com.example.tbcacademyhomework.domain.auth.validation.UserDataValidator
import com.example.tbcacademyhomework.domain.users.models.User
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindsUserDataValidator(impl: UserDataValidatorImpl): UserDataValidator

    companion object {
        @Provides
        fun provideAuthService(
            retrofit: Retrofit
        ): AuthApiService {
            return retrofit.create(AuthApiService::class.java)
        }
    }
}