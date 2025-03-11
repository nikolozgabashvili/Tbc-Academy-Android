package com.example.tbcacademyhomework.domain.users.usecase

import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import javax.inject.Inject

interface GetLocalUsersUseCase {
    suspend operator fun invoke(page: Int): List<User>
}

class GetLocalUsersUseCaseImpl @Inject constructor(
    private val usersLocalRepository: UsersLocalRepository
) : GetLocalUsersUseCase {
    override suspend fun invoke(page: Int): List<User> {
        return usersLocalRepository.getUsers(page)
    }
}