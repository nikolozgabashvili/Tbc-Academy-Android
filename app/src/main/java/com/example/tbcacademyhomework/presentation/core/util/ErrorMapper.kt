package com.example.tbcacademyhomework.presentation.core.util

import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.core.util.NetworkError

fun NetworkError.toGenericString(): GenericString {
    return when (this) {
        NetworkError.ErrorType.CONNECTION_ERROR -> GenericString.Resource(stringRes = R.string.network_error)
        NetworkError.ErrorType.SERIALIZATION_ERROR -> GenericString.Resource(stringRes = R.string.serialization_error)
        NetworkError.ErrorType.UNKNOWN_ERROR -> GenericString.Resource(stringRes = R.string.unknown_error)
        NetworkError.ErrorType.UNKNOWN_HOST -> GenericString.Resource(stringRes = R.string.unknown_host)
        NetworkError.ErrorType.REQUEST_TIMEOUT -> GenericString.Resource(stringRes = R.string.request_timeout)
        is NetworkError.TextError -> GenericString.Literal(this.message ?: "")
    }
}