package com.example.tbcacademyhomework.network

import com.example.tbcacademyhomework.network.models.NetworkError
import kotlinx.serialization.json.Json
import retrofit2.Response

fun Response<*>.parseError(): String? {
    return try {
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        val errorBody = errorBody()?.string()
        if (errorBody.isNullOrEmpty()) {
            return null
        }
        json.decodeFromString<NetworkError>(errorBody).error
    } catch (e: Exception) {
        println(e)
        null
    }
}