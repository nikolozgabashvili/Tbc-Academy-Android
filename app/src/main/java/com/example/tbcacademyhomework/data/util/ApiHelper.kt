package com.example.tbcacademyhomework.data.util


import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class ApiHelper {

    fun <T> safeCall(
        call: suspend () -> Response<T>
    ): Flow<Resource<T, NetworkError>> {
        return flow {
            try {
                emit(Resource.Loading)
                val response = call()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it))
                    } ?: emit(Resource.Error(NetworkError.DataError.UNKNOWN_ERROR))
                } else {
                    emit(Resource.Error(NetworkError.StringError(response.message())))
                }

            } catch (throwable: Throwable) {
                emit(Resource.Error(NetworkError.StringError(throwable.message)))

            }
        }.flowOn(Dispatchers.IO)

    }

}