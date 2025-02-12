package com.example.tbcacademyhomework.data.remote.repository

import com.example.tbcacademyhomework.data.mappers.toDomain
import com.example.tbcacademyhomework.data.network.service.UsersApiService
import com.example.tbcacademyhomework.data.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.remote.models.UsersList
import com.example.tbcacademyhomework.domain.remote.repository.UsersRemoteSource
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRemoteSourceImpl(
    private val usersApiService: UsersApiService
) : UsersRemoteSource {
    override suspend fun getUsers(page: Int): Resource<UsersList, DataError> {
        return withContext(Dispatchers.IO) {
            HttpRequestHelper.safeCall {
                usersApiService.fetchUsers(page)
            }.map {
                it.toDomain()
            }
        }
    }

}