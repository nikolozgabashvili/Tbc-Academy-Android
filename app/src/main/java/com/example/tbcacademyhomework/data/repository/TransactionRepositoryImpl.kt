package com.example.tbcacademyhomework.data.repository

import com.example.tbcacademyhomework.data.mapper.toDomain
import com.example.tbcacademyhomework.data.service.TransferApiService
import com.example.tbcacademyhomework.data.util.ApiHelper
import com.example.tbcacademyhomework.domain.model.transaction.TransactionStatusDomain
import com.example.tbcacademyhomework.domain.model.transaction.TransferMoneyParamDomain
import com.example.tbcacademyhomework.domain.repository.TransactionRepository
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val apiService: TransferApiService,
    private val apiHelper: ApiHelper
) : TransactionRepository {

    override suspend fun transferMoney(params: TransferMoneyParamDomain): Flow<Resource<TransactionStatusDomain, NetworkError>> {
        return apiHelper.safeCall {
            apiService.transferMoney(
                fromAccount = params.fromAccount,
                toAccount = params.toAccount,
                money = params.amount
            )
        }.mapResource {
            it.toDomain()
        }
    }
}