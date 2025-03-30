package com.example.tbcacademyhomework.domain.repository

import com.example.tbcacademyhomework.domain.model.transaction.TransactionStatusDomain
import com.example.tbcacademyhomework.domain.model.transaction.TransferMoneyParamDomain
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun transferMoney(params: TransferMoneyParamDomain): Flow<Resource<TransactionStatusDomain, NetworkError>>
}