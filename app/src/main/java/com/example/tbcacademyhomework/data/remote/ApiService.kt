package com.example.tbcacademyhomework.data.remote

import com.example.tbcacademyhomework.data.remote.model.UsersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(USERS)
    suspend fun getUsers(): Response<UsersResponseDto>


    companion object{
        private const val USERS = "f3f41821-7434-471f-9baa-ae3dee984e6d"
    }
}