package com.example.tbcacademyhomework.data.category.di

import com.example.tbcacademyhomework.data.category.api_service.EquipmentCategoryApiService
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
    fun provideCategoryApiService(
        retrofit: Retrofit
    ): EquipmentCategoryApiService {
        return retrofit.create(EquipmentCategoryApiService::class.java)
    }
}