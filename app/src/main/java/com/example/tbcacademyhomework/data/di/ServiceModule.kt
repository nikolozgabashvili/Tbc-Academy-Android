package com.example.tbcacademyhomework.data.di

import com.example.tbcacademyhomework.data.service.AccountApiService
import com.example.tbcacademyhomework.data.service.CurrencyApiService
import com.example.tbcacademyhomework.data.service.TransferApiService
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
    fun provideAccountApiService(
        retrofit: Retrofit
    ): AccountApiService {
        return retrofit.create(AccountApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyApiService(
        retrofit: Retrofit
    ): CurrencyApiService {
        return retrofit.create(CurrencyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTransferApiService(
        retrofit: Retrofit
    ): TransferApiService {
        return retrofit.create(TransferApiService::class.java)
    }
}