package com.example.tbcacademyhomework.domain.auth.util

import com.example.tbcacademyhomework.domain.core.util.Error

enum class FirebaseError : Error {
    UNKNOWN,
    INVALID_CREDENTIALS,
    INVALID_EMAIL,
    NETWORK_ERROR,
    USER_COLLISION
}