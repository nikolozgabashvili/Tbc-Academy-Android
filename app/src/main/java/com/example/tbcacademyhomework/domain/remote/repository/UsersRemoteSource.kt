package com.example.tbcacademyhomework.domain.remote.repository

import com.example.tbcacademyhomework.domain.remote.models.UsersList
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource

interface UsersRemoteSource {
    suspend fun getUsers(page: Int): Resource<UsersList, DataError>
}