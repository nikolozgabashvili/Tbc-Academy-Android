package com.example.tbcacademyhomework.data.model.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BankAccountByUserDTO(
    val id: Int,
    @SerialName("personal_number")
    val personalNumber: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("account_number")
    val accountNumber: String,
    @SerialName("account_id")
    val accountId: Int
)