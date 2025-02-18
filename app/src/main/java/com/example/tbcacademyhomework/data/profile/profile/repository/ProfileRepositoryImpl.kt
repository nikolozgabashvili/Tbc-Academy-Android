package com.example.tbcacademyhomework.data.profile.profile.repository

import com.example.tbcacademyhomework.domain.core.repository.UserPrefsRepository
import com.example.tbcacademyhomework.domain.profile.repository.ProfileRepository
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDataSource: UsersLocalRepository,
    private val userPrefsRepository: UserPrefsRepository,
) : ProfileRepository {

    override suspend fun clearUser() {
        localDataSource.deleteData()
        userPrefsRepository.clearData()
    }

    override suspend fun getUserEmail(): String? {
        return userPrefsRepository.getUserEmail()
    }

}