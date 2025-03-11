package com.example.tbcacademyhomework.domain.users.usecase

import com.example.tbcacademyhomework.domain.users.models.User
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import javax.inject.Inject

interface UpdateLocalUsersUseCase {

    suspend operator fun invoke(page: Int, users: List<User>): List<User>
}

class UpdateLocalUsersUseCaseImpl @Inject constructor(
    private val usersLocalRepository: UsersLocalRepository
) : UpdateLocalUsersUseCase {
    override suspend fun invoke(page: Int, users: List<User>): List<User> {
        return usersLocalRepository.updateUsers(page, users)
    }
}