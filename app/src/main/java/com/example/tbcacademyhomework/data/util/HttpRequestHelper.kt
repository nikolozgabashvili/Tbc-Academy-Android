package com.example.tbcacademyhomework.data.util

import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    fun <T> safeCall(
        enableLoading: Boolean = true,
        call: suspend () -> Response<T>
    ): Flow<Resource<T, DataError>> {
        return flow {
            try {
                if (enableLoading)
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
                emit(Resource.Error(DataError.Literal(throwable.message)))

            }
        }.flowOn(Dispatchers.IO)

    }


    private fun parseError(response: Response<*>): String? {
        val errorString = response.errorBody()?.string()
        return errorString?.let { json.decodeFromString<ApiError>(it).error }
    }
}
