package com.example.tbcacademyhomework.util

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val errorType: ErrorType? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(
        error: String?,
        errorType: ErrorType? = null
    ) : Resource<T>(null, error, errorType)

    class Loading<T> : Resource<T>()
}


fun <T> Resource<T>?.isError(): Boolean = this is Resource.Error
fun <T> Resource<T>?.isSuccess(): Boolean = this is Resource.Success
fun <T> Resource<T>?.isLoading(): Boolean = this is Resource.Loading
