package com.example.tbcacademyhomework.network

import com.example.tbcacademyhomework.network.model.AuthParams
import com.example.tbcacademyhomework.network.model.ResponseDto
import com.example.tbcacademyhomework.network.model.UsersListDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(LOGIN)
    suspend fun loginUser(@Body authParams: AuthParams): Response<ResponseDto>


    @POST(REGISTER)
    suspend fun registerUser(@Body authParams: AuthParams): Response<ResponseDto>

    @GET(USERS)
    suspend fun fetchUsers(@Query("page") page: Int): Response<UsersListDto>


    companion object {
        private const val LOGIN = "login"
        private const val REGISTER = "register"
        private const val USERS = "users"

    }
}