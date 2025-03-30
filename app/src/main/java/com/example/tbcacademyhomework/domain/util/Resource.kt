package com.example.tbcacademyhomework.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias ResourceError = Error

sealed interface Resource<out D, out E : ResourceError> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Error<out E : ResourceError>(val error: E) : Resource<Nothing, E>
    data object Loading : Resource<Nothing, Nothing>
}

fun <D, E : ResourceError, R> Resource<D, E>.map(transform: (D) -> R): Resource<R, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(error)
        Resource.Loading -> Resource.Loading
    }
}

fun <D, E : ResourceError, R> Flow<Resource<D, E>>.mapResource(transform: (D) -> R): Flow<Resource<R, E>> {
    return this.map { it.map(transform) }
}

fun Resource<*, *>.isSuccess() = this is Resource.Success
fun Resource<*, *>.isLoading() = this is Resource.Loading
fun Resource<*, *>.isError() = this is Resource.Error
