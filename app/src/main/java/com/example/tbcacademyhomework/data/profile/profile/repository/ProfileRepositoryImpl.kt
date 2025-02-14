package com.example.tbcacademyhomework.data.profile.profile.repository

import com.example.tbcacademyhomework.data.users.database.AppDatabase
import com.example.tbcacademyhomework.domain.core.repository.UserPrefsRepository
import com.example.tbcacademyhomework.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val userPrefsRepository: UserPrefsRepository,
):ProfileRepository{

    override suspend fun clearUser() {
        appDatabase.userDao.clearAll()
        userPrefsRepository.clearData()
    }

}