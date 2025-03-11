package com.example.tbcacademyhomework.data.auth.di

import com.example.tbcacademyhomework.data.auth.service.LoginApiService
import com.example.tbcacademyhomework.data.auth.service.RegisterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providesLoginService(
        retrofit: Retrofit
    ): LoginApiService {
        return retrofit.create(LoginApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideRegisterService(
        retrofit: Retrofit
    ): RegisterApiService {
        return retrofit.create(RegisterApiService::class.java)
    }

}