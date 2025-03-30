package com.example.tbcacademyhomework.presentation.common.util

import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.util.NetworkError

fun NetworkError.toGenericString(): GenericString {
    return when (this) {
        is NetworkError.DataError -> GenericString.Resource(getResource())
        is NetworkError.StringError -> {
            GenericString.Literal(error)
        }
    }
}

private fun NetworkError.DataError.getResource(): Int {
    return when (this) {
        NetworkError.DataError.NETWORK_ERROR -> R.string.network_error
        NetworkError.DataError.SERIALIZATION_ERROR -> R.string.serialization_error
        NetworkError.DataError.UNKNOWN_ERROR -> R.string.unknown_error
    }
}