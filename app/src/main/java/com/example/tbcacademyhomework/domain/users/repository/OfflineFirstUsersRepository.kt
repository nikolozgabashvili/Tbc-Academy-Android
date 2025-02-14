package com.example.tbcacademyhomework.domain.users.repository

import com.example.tbcacademyhomework.domain.users.models.PagedUsers
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface OfflineFirstUsersRepository {
    val eventFlow:Flow<Resource<Nothing,DataError>>
    suspend fun fetchUsers(page: Int): Flow<Resource<PagedUsers, DataError>>
}