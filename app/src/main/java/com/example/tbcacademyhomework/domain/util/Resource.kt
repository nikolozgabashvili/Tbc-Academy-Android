package com.example.tbcacademyhomework.domain.util

private typealias NetworkError = Error

sealed interface Resource<out D, out E : NetworkError> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Error<out E : NetworkError>(val error: E) : Resource<Nothing, E>
    data object Loading : Resource<Nothing, Nothing>




}

inline fun <T, E : NetworkError, R> Resource<T, E>.map(transform: (T) -> R): Resource<R, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(error)
        Resource.Loading -> Resource.Loading
    }
}


fun Resource<*, *>.isSuccess() = this is Resource.Success
fun Resource<*, *>.isError() = this is Resource.Error
fun Resource<*, *>.isLoading() = this is Resource.Loading