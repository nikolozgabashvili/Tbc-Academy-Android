package com.example.tbcacademyhomework.domain.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import retrofit2.Response
import java.net.UnknownHostException
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
                e.printStackTrace()
                when (e) {
                    is SerializationException -> {

                        emit(Resource.Error(DataError.DynamicError.SERIALIZATION_ERROR))
                    }

                    is UnknownHostException -> {
                        emit(Resource.Error(DataError.DynamicError.UNKNOWN_HOST))
                    }

                    else -> {
                        emit(Resource.Error(DataError.DynamicError.UNKNOWN))
                    }
                }

            }

        }.flowOn(Dispatchers.IO)
    }
}