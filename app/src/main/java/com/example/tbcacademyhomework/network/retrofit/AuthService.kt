package com.example.tbcacademyhomework.network.retrofit

import com.example.tbcacademyhomework.network.models.AuthDto
import com.example.tbcacademyhomework.network.models.AuthParams
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(LOGIN_ENDPOINT)
    suspend fun loginUser(@Body params: AuthParams): Response<AuthDto>


    @POST(REGISTER_ENDPOINT)
    suspend fun registerUser(@Body params: AuthParams): Response<AuthDto>


    companion object {
        private const val LOGIN_ENDPOINT = "login"
        private const val REGISTER_ENDPOINT = "register"

    }
}