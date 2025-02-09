package com.example.tbcacademyhomework.data.network.retrofit

import com.example.tbcacademyhomework.data.network.service.AuthApiService
import com.example.tbcacademyhomework.data.network.service.UsersApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitProvider {

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

    private fun getRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val authApiService: AuthApiService = getRetrofit().create(AuthApiService::class.java)
    val usersApiService: UsersApiService = getRetrofit().create(UsersApiService::class.java)

    private const val BASE_URL = "https://reqres.in/api/"
}