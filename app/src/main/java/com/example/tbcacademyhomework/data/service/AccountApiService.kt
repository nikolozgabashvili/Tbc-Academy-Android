package com.example.tbcacademyhomework.data.service

import com.example.tbcacademyhomework.data.model.account.BankAccountByUserDTO
import com.example.tbcacademyhomework.data.model.account.BankAccountDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountApiService {
    @GET(GET_ACCOUNTS)
    suspend fun getAccounts(): Response<List<BankAccountDTO>>

    @GET(GET_ACCOUNT_BY_INFO)
    suspend fun getAccountByInfo(@Query(ACCOUNT_NUMBER) userInfo: String):Response<List<BankAccountByUserDTO>>

    companion object {
        private const val GET_ACCOUNTS = "d689fe3e-6faf-446a-9896-c538de3449fa"
        private const val GET_ACCOUNT_BY_INFO = "5fb8d70f-043f-4ddd-b3d3-7d7cdd34eecc"
        private const val ACCOUNT_NUMBER = "account_number"
    }
}