package com.example.tbcacademyhomework.data.mapper

import com.example.tbcacademyhomework.data.model.account.TransactionStatusDTO
import com.example.tbcacademyhomework.domain.model.transaction.StatusType
import com.example.tbcacademyhomework.domain.model.transaction.TransactionStatusDomain

fun TransactionStatusDTO.toDomain(): TransactionStatusDomain {
    return TransactionStatusDomain(
        status = StatusType.getStatus(status)
    )
}