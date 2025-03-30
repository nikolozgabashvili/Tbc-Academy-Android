package com.example.tbcacademyhomework.domain.usecase

import com.example.tbcacademyhomework.domain.model.account.BankAccountDomain
import com.example.tbcacademyhomework.domain.repository.AccountRepository
import com.example.tbcacademyhomework.domain.util.NetworkError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<BankAccountDomain>, NetworkError>> {
        return accountRepository.getUserAccounts()
    }
}