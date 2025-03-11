package com.example.tbcacademyhomework.domain.users.usecase

import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import javax.inject.Inject

interface ClearLocalDataUseCase {
    suspend operator fun invoke()
}

class ClearLocalDataUseCaseImpl @Inject constructor(
    private val usersLocalRepository: UsersLocalRepository
) : ClearLocalDataUseCase {
    override suspend fun invoke() {
        usersLocalRepository.deleteData()
    }
}