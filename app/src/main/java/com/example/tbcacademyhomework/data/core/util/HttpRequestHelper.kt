package com.example.tbcacademyhomework.data.core.util

import com.example.tbcacademyhomework.domain.core.util.DataError
import com.example.tbcacademyhomework.domain.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HttpRequestHelper @Inject constructor() {
    fun <T> safeCall(call: suspend () -> Response<T>): Flow<Resource<T, DataError>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response = call()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it))
                    } ?: run { emit(Resource.Error(DataError.DynamicError.UNKNOWN)) }
                } else {
                    val error = response.errorBody()?.string()
                    emit(Resource.Error(DataError.Literal(error)))
                }

            } catch (e: Exception) {
                when (e) {
                    else -> {
                        emit(Resource.Error(DataError.DynamicError.UNKNOWN))
                    }
                }

            }

        }
    }
}