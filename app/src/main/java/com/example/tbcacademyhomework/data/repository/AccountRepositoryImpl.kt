package com.example.tbcacademyhomework.data.repository

import com.example.tbcacademyhomework.data.mapper.getMatchingId
import com.example.tbcacademyhomework.data.mapper.toDomainList
import com.example.tbcacademyhomework.data.service.AccountApiService
import com.example.tbcacademyhomework.data.util.ApiHelper
import com.example.tbcacademyhomework.domain.model.account.BankAccountDomain
import com.example.tbcacademyhomework.domain.repository.AccountRepository
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiService: AccountApiService,
    private val apiHelper: ApiHelper
) : AccountRepository {

    override suspend fun getUserAccounts(): Flow<Resource<List<BankAccountDomain>, NetworkError>> {
        return apiHelper.safeCall {
            apiService.getAccounts()
        }.mapResource { it.toDomainList() }
    }

    override suspend fun getAccountForUser(userInfo: String): Flow<Resource<Int?, NetworkError>> {
        return apiHelper.safeCall {
            apiService.getAccountByInfo(userInfo)
        }.mapResource {
            it.getMatchingId(userInfo)
        }

    }
}