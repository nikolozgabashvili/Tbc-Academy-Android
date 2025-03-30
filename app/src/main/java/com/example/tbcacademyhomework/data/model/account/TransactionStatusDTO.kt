package com.example.tbcacademyhomework.data.model.account

import kotlinx.serialization.Serializable

@Serializable
data class TransactionStatusDTO(
    val status: String
)