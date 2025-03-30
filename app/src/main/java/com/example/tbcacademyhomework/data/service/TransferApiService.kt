package com.example.tbcacademyhomework.data.service

import com.example.tbcacademyhomework.data.model.account.TransactionStatusDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TransferApiService {
    @GET(TRANSFER)
    suspend fun transferMoney(
        @Query(FROM_ACCOUNT) fromAccount: String,
        @Query(TO_ACCOUNT) toAccount: String,
        @Query(MONEY) money: Double
    ): Response<TransactionStatusDTO>

    companion object {
        private const val TRANSFER = "29d002d4-3ccd-4eaa-95eb-a9d1601ce123"
        private const val FROM_ACCOUNT = "from_account"
        private const val TO_ACCOUNT = "to_account"
        private const val MONEY = "money"
    }
}