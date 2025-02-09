package com.example.tbcacademyhomework.data.network.service

import com.example.tbcacademyhomework.data.users.models.UsersListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApiService {

    @GET(USERS)
    suspend fun fetchUsers(@Query("page") page: Int): Response<UsersListDto>

    companion object {
        private const val USERS = "users"
    }
}