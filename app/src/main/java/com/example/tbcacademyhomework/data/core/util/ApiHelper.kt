package com.example.tbcacademyhomework.data.core.util

import com.example.tbcacademyhomework.domain.core.util.NetworkError
import com.example.tbcacademyhomework.domain.core.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import retrofit2.Response
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

class ApiHelper {

    fun <T> safeCall(call: suspend () -> Response<T>): Flow<Resource<T, NetworkError>> {
        return flow {
            try {
                emit(Resource.Loading)
                val response = call()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        emit(Resource.Success(data))
                    } ?: run {
                        emit(Resource.Error(NetworkError.ErrorType.UNKNOWN_ERROR))
                    }
                } else {
                    emit(handleResponseCode(response.code()))
                }

            } catch (e: Exception) {
                val error = when (e) {
                    is CancellationException -> throw e
                    is SerializationException -> NetworkError.ErrorType.SERIALIZATION_ERROR
                    is UnknownHostException -> NetworkError.ErrorType.CONNECTION_ERROR
                    else -> {
                        NetworkError.ErrorType.UNKNOWN_ERROR
                    }
                }
                emit(Resource.Error(error))

            }
        }.flowOn(Dispatchers.IO)
    }

    private fun <T> handleResponseCode(code: Int): Resource<T, NetworkError> {
        val error = when (code) {
            404 -> NetworkError.ErrorType.UNKNOWN_HOST
            408 -> NetworkError.ErrorType.REQUEST_TIMEOUT
            else -> NetworkError.ErrorType.UNKNOWN_ERROR
        }
        return Resource.Error(error)
    }
}