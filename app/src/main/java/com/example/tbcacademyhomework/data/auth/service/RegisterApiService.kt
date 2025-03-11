package com.example.tbcacademyhomework.data.auth.service

import com.example.tbcacademyhomework.data.auth.models.RegisterResponseDTO
import com.example.tbcacademyhomework.data.auth.models.AuthUserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {

    @POST(REGISTER)
    suspend fun registerUser(@Body authParams: AuthUserRequest): Response<RegisterResponseDTO>

    companion object {
        private const val REGISTER = "register"
    }

}