package com.example.tbcacademyhomework.domain.core.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias ResourceError = Error

sealed interface Resource<out D, out E : ResourceError> {
    /**
     * Represents a successful resource containing data.
     *
     * @param D The type of the data.
     * @property data The data contained in the resource.
     */
    data class Success<out D>(val data: D) : Resource<D, Nothing>

    /**
     * Represents an error resource containing an error.
     *
     * @param E The type of the error.
     * @property error The error contained in the resource.
     */
    data class Error<out E : ResourceError>(val error: E) : Resource<Nothing, E>


    data object Loading : Resource<Nothing, Nothing>
}

/**
 * Extension function to transform the data in a Resource.
 *
 * @param T The type of the original data.
 * @param R The type of the transformed data.
 * @param E The type of the error.
 * @param transform A function to transform the data.
 * @return A new Resource with the transformed data.
 */
fun <T, R, E : Error> Resource<T, E>.map(transform: (T) -> R): Resource<R, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(error)
        Resource.Loading -> Resource.Loading
    }
}

/**
 * Extension function to transform the data in a Flow of Resource using [Resource.map] function.
 */
fun <T, R, E : Error> Flow<Resource<T, E>>.mapResource(transform: (T) -> R): Flow<Resource<R, E>> {
    return this.map { it.map(transform) }
}

fun Resource<*, *>.isSuccess() = this is Resource.Success
fun Resource<*, *>.isError() = this is Resource.Error
fun Resource<*, *>.isLoading() = this is Resource.Loading
