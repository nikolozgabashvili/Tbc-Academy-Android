package com.example.tbcacademyhomework.domain.repository

import com.example.tbcacademyhomework.domain.model.account.BankAccountDomain
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getUserAccounts(): Flow<Resource<List<BankAccountDomain>, NetworkError>>
    suspend fun getAccountForUser(
        userInfo: String
    ): Flow<Resource<Int?, NetworkError>>
}