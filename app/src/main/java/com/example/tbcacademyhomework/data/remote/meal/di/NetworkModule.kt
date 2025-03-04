package com.example.tbcacademyhomework.data.remote.meal.di

import com.example.tbcacademyhomework.BuildConfig
import com.example.tbcacademyhomework.data.remote.core.util.HttpRequestHelper
import com.example.tbcacademyhomework.data.remote.meal.service.MealApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG)
                addInterceptor(loggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MealApiService {
        return retrofit.create(MealApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideHttpRequestHelper(): HttpRequestHelper {
        return HttpRequestHelper()
    }


    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

}