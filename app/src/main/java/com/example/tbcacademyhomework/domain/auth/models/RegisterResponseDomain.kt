package com.example.tbcacademyhomework.domain.auth.models

data class RegisterResponseDomain(
    val token: String,
    val id: Int? = null
)
