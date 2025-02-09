package com.example.tbcacademyhomework.domain.local

import kotlinx.coroutines.flow.Flow

interface UserPrefsDataSource {
    suspend fun getUserToken(): Flow<String?>
    suspend fun getUserEmail(): Flow<String?>
    suspend fun saveToken(token: String)
    suspend fun savEmail(email: String)
    suspend fun clearData()
}