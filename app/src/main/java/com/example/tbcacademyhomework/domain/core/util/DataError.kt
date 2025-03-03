package com.example.tbcacademyhomework.domain.core.util

sealed interface DataError : Error {
    data class Literal(val errorString: String?) : DataError
    enum class DynamicError : DataError {
        UNKNOWN
    }
}