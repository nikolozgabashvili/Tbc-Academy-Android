package com.example.tbcacademyhomework.domain.repository

import com.example.tbcacademyhomework.domain.model.account.CurrencyType
import com.example.tbcacademyhomework.domain.model.exchange.ExchangeCourseDomain
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    suspend fun getExchangeRate(
        fromAccount: CurrencyType,
        toAccount: CurrencyType
    ): Flow<Resource<ExchangeCourseDomain, NetworkError>>
}