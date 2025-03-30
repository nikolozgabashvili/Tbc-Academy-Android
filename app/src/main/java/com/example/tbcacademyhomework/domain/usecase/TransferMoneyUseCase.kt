package com.example.tbcacademyhomework.domain.usecase

import com.example.tbcacademyhomework.domain.model.transaction.TransactionStatusDomain
import com.example.tbcacademyhomework.domain.model.transaction.TransferMoneyParamDomain
import com.example.tbcacademyhomework.domain.repository.TransactionRepository
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferMoneyUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(params:TransferMoneyParamDomain): Flow<Resource<TransactionStatusDomain, NetworkError>> {
        return transactionRepository.transferMoney(params = params)
    }
}