package com.example.tbcacademyhomework.domain.usecase

import com.example.tbcacademyhomework.domain.model.account.CurrencyType
import com.example.tbcacademyhomework.domain.model.exchange.ExchangeCourseDomain
import com.example.tbcacademyhomework.domain.repository.ExchangeRepository
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) {
    suspend operator fun invoke(
        fromAccount: CurrencyType,
        toAccount: CurrencyType
    ): Flow<Resource<ExchangeCourseDomain, NetworkError>> {
        return exchangeRepository.getExchangeRate(
            fromAccount = fromAccount,
            toAccount = toAccount
        )
    }
}