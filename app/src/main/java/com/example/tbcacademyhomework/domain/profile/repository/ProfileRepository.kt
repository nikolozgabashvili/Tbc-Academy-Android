package com.example.tbcacademyhomework.domain.profile.repository

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun clearUser()
    suspend fun getUserEmail(): Flow<String?>
}