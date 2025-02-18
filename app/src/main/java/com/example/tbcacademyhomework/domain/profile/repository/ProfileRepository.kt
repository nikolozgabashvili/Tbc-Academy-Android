package com.example.tbcacademyhomework.domain.profile.repository

interface ProfileRepository {
    suspend fun clearUser()
    suspend fun getUserEmail(): String?
}