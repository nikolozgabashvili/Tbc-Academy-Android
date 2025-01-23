package com.example.tbcacademyhomework.network

import com.example.tbcacademyhomework.network.model.AuthParams
import com.example.tbcacademyhomework.network.model.ResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(LOGIN)
    suspend fun loginUser(@Body authParams: AuthParams): Response<ResponseDto>


    @POST(REGISTER)
    suspend fun registerUser(@Body authParams: AuthParams): Response<ResponseDto>


    companion object {
        private const val LOGIN = "login"
        private const val REGISTER = "register"

    }
}