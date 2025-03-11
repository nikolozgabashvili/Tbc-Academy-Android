package com.example.tbcacademyhomework.data.users.repository

import com.example.tbcacademyhomework.data.users.service.UsersApiService
import com.example.tbcacademyhomework.data.users.util.toDomain
import com.example.tbcacademyhomework.data.util.HttpRequestHelper
import com.example.tbcacademyhomework.domain.users.models.UsersList
import com.example.tbcacademyhomework.domain.users.repository.UsersRemoteRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.map
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRemoteRepositoryImpl @Inject constructor(
    private val usersApiService: UsersApiService,
    private val httpRequestHelper: HttpRequestHelper
) : UsersRemoteRepository {
    override suspend fun getUsers(page: Int): Resource<UsersList, DataError> {
        return httpRequestHelper.safeCall(enableLoading = false) {
            usersApiService.fetchUsers(page)
        }.map {
            it.map {
                it.toDomain()
            }
        }.first()

    }

}