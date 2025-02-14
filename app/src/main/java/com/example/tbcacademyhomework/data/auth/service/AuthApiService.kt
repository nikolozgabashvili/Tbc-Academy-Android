package com.example.tbcacademyhomework.data.auth.service

import com.example.tbcacademyhomework.data.auth.models.AuthResponseDto
import com.example.tbcacademyhomework.data.auth.models.AuthUserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST(LOGIN)
    suspend fun loginUser(@Body user: AuthUserRequest): Response<AuthResponseDto>

    @POST(REGISTER)
    suspend fun registerUser(@Body authParams: AuthUserRequest): Response<AuthResponseDto>

    companion object {
        private const val LOGIN = "login"
        private const val REGISTER = "register"

    }

}