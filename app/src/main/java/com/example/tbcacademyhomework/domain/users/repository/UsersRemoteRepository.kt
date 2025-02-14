package com.example.tbcacademyhomework.domain.users.repository

import com.example.tbcacademyhomework.domain.users.models.UsersList
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource

interface UsersRemoteRepository {
    suspend fun getUsers(page: Int): Resource<UsersList, DataError>
}