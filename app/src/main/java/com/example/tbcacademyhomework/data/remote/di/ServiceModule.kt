package com.example.tbcacademyhomework.data.remote.di

import com.example.tbcacademyhomework.data.remote.LocationsApiService
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
    fun provideLocationsApiService(
        retrofit: Retrofit
    ): LocationsApiService {
        return retrofit.create(LocationsApiService::class.java)
    }

}