package com.example.tbcacademyhomework.network.models

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null,
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(
        error: String?,
    ) : Resource<T>(null, error)
}


fun <T> Resource<T>?.isError(): Boolean = this is Resource.Error
fun <T> Resource<T>?.isSuccess(): Boolean = this is Resource.Success