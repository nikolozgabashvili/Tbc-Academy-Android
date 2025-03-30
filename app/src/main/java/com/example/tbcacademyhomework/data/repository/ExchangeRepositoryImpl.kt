package com.example.tbcacademyhomework.data.repository

import com.example.tbcacademyhomework.data.mapper.toDomain
import com.example.tbcacademyhomework.data.service.CurrencyApiService
import com.example.tbcacademyhomework.data.util.ApiHelper
import com.example.tbcacademyhomework.domain.model.account.CurrencyType
import com.example.tbcacademyhomework.domain.model.exchange.ExchangeCourseDomain
import com.example.tbcacademyhomework.domain.repository.ExchangeRepository
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val apiService: CurrencyApiService,
    private val apiHelper: ApiHelper
) : ExchangeRepository {
    override suspend fun getExchangeRate(
        fromAccount: CurrencyType,
        toAccount: CurrencyType
    ): Flow<Resource<ExchangeCourseDomain, NetworkError>> {
        return apiHelper.safeCall {
            apiService.getCurrencyCourse(fromAccount.name, toAccount.name)
        }.mapResource {
            it.toDomain()
        }
    }
}