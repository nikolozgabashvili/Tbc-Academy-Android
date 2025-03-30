package com.example.tbcacademyhomework.data.di


import com.example.tbcacademyhomework.BuildConfig
import com.example.tbcacademyhomework.data.util.ApiHelper
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
object AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }


    @Provides
    @Singleton
    fun providesRetrofit(
        httpClient: OkHttpClient,
        json: Json
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
    fun providesOkHttpClient(): OkHttpClient {
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
    fun provideApiHelper(): ApiHelper {
        return ApiHelper()
    }

}