package com.example.tbcacademyhomework.data.service

import com.example.tbcacademyhomework.data.model.account.ExchangeCourseDTO
import retrofit2.Response

import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET(CURRENCY_COURSE)
    suspend fun getCurrencyCourse(
        @Query(FROM_ACCOUNT) fromAccount: String,
        @Query(TO_ACCOUNT) toAccount: String
    ): Response<ExchangeCourseDTO>

    companion object {
        private const val CURRENCY_COURSE = "a78769e5-98ef-4a56-a3d4-ed7683447806"
        private const val FROM_ACCOUNT = "from_account"
        private const val TO_ACCOUNT = "to_account"
    }
}