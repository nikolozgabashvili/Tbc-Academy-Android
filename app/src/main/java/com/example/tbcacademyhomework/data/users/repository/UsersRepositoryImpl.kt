package com.example.tbcacademyhomework.data.users.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tbcacademyhomework.data.network.service.UsersApiService
import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.domain.users.repository.UsersRepository
import com.example.tbcacademyhomework.presentation.screens.users.paging.UsersPagingSource
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(
    private val usersApiService: UsersApiService
) : UsersRepository {
    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { UsersPagingSource(usersApiService) }
        ).flow
    }

}