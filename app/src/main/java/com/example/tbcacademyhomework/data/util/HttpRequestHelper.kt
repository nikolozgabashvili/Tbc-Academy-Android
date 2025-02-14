package com.example.tbcacademyhomework.data.util

import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.serialization.json.Json
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpRequestHelper @Inject constructor() {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    suspend fun <T> safeCall(call: suspend () -> Response<T>): Resource<T, DataError> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error(DataError.DynamicError.UNKNOWN)
            } else {
                val error = parseError(response)
                Resource.Error(DataError.Literal(error))
            }

        } catch (throwable: Throwable) {
            Resource.Error(DataError.Literal(throwable.message))

        }

    }


    private fun parseError(response: Response<*>): String? {
        val errorString = response.errorBody()?.string()
        return errorString?.let { json.decodeFromString<ApiError>(it).error }
    }
}
