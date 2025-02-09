package com.example.tbcacademyhomework.data.util

import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import retrofit2.Response
import kotlin.coroutines.coroutineContext

object HttpRequestHelper {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    fun <T> executeCall(call: suspend () -> Response<T>): Flow<Resource<T, DataError>> {
        return flow {
            try {
                emit(Resource.Loading)
                val response = call()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it))
                    } ?: emit(Resource.Error(DataError.DynamicError.UNKNOWN))
                } else {
                    val error = parseError(response)
                    emit(Resource.Error(DataError.Literal(error)))
                }

            } catch (throwable: Throwable) {
                coroutineContext.ensureActive()
                emit(Resource.Error(DataError.Literal(throwable.message)))

            }

        }
    }

    fun parseError(response: Response<*>): String? {
        val errorString = response.errorBody()?.string()
        return errorString?.let { json.decodeFromString<ApiError>(it).error }
    }
}