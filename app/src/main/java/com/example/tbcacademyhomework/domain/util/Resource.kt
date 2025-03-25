package com.example.tbcacademyhomework.domain.util

sealed interface Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>
    data class Error(val error: String?) : Resource<Nothing>
    data object Loading : Resource<Nothing>
}