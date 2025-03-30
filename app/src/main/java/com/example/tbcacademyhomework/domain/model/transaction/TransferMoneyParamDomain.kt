package com.example.tbcacademyhomework.domain.model.transaction

data class TransferMoneyParamDomain(
    val fromAccount: String,
    val toAccount: String,
    val amount: Double
)
