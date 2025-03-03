package com.example.tbcacademyhomework.presentation.core.validation

import java.util.UUID

data class ValidationItem(
    val id: String = UUID.randomUUID().toString(),
    val isValid: Boolean,
    val message: String
)
