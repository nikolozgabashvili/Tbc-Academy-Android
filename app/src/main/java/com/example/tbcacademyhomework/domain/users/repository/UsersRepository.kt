package com.example.tbcacademyhomework.domain.users.repository

import androidx.paging.PagingData
import com.example.tbcacademyhomework.domain.users.models.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<PagingData<User>>
}