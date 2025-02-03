package com.example.tbcacademyhomework.presentation.user

import com.example.tbcacademyhomework.data.remote.model.UsersResponseDto
import com.example.tbcacademyhomework.util.Resource

sealed interface UserEvents {
    data object NoNetwork : UserEvents
    data object YesNetwork : UserEvents
    data class Error(val error: Resource<UsersResponseDto>) : UserEvents
}