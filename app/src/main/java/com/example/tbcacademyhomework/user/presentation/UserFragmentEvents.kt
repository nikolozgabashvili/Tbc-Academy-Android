package com.example.tbcacademyhomework.user.presentation

import com.example.tbcacademyhomework.error.UserValidationError

sealed interface UserFragmentEvents {
    data object Success:UserFragmentEvents
    data class Error(val error: UserValidationError):UserFragmentEvents
}