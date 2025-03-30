package com.example.tbcacademyhomework.domain.usecase

import com.example.tbcacademyhomework.domain.repository.AccountRepository
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountByUserInfoUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(userInfo: String): Flow<Resource<Int?, NetworkError>> {
        return accountRepository.getAccountForUser(userInfo = userInfo)
    }
}