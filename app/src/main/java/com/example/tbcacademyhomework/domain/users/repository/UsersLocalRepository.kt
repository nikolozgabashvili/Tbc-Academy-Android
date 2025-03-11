package com.example.tbcacademyhomework.domain.users.repository

import com.example.tbcacademyhomework.domain.users.models.User

interface UsersLocalRepository {
    suspend fun getUsers(page: Int): List<User>
    suspend fun updateUsers(page: Int, users: List<User>): List<User>
    suspend fun deleteData()

}