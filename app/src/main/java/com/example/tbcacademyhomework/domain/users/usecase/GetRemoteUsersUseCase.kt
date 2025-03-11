package com.example.tbcacademyhomework.domain.users.usecase

import com.example.tbcacademyhomework.domain.users.models.UsersList
import com.example.tbcacademyhomework.domain.users.repository.UsersRemoteRepository
import com.example.tbcacademyhomework.domain.utils.DataError
import com.example.tbcacademyhomework.domain.utils.Resource
import javax.inject.Inject

interface GetRemoteUsersUseCase {
    suspend operator fun invoke(page: Int): Resource<UsersList, DataError>
}

class  GetRemoteUsersUseCaseImpl @Inject constructor(
    private val usersRemoteRepository: UsersRemoteRepository
) : GetRemoteUsersUseCase {
    override suspend fun invoke(page: Int): Resource<UsersList, DataError> {
        return usersRemoteRepository.getUsers(page)
    }
}