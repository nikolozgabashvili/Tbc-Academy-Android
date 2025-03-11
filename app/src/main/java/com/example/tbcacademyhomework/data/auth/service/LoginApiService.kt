package com.example.tbcacademyhomework.data.auth.service

import com.example.tbcacademyhomework.data.auth.models.AuthUserRequest
import com.example.tbcacademyhomework.data.auth.models.LoginResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST(LOGIN)
    suspend fun loginUser(@Body user: AuthUserRequest): Response<LoginResponseDTO>

    companion object {
        private const val LOGIN = "login"
    }
}