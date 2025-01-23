package com.example.tbcacademyhomework.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitImpl {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()

            .addInterceptor(loggingInterceptor)
            .build()
    }


    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val authApiService: ApiService = provideRetrofit().create(ApiService::class.java)


    private const val BASE_URL = "https://reqres.in/api/"

}