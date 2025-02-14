package com.example.tbcacademyhomework.data.util

import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpRequestHelper @Inject constructor() {

    suspend fun <T> safeCall(call: suspend () -> Response<T>): Resource<T, DataError> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error(DataError.DynamicError.UNKNOWN)
            } else {

                Resource.Error(DataError.Literal(response.errorBody().toString()))
            }

        } catch (throwable: Throwable) {
            Resource.Error(DataError.Literal(throwable.message))

        }

    }


}