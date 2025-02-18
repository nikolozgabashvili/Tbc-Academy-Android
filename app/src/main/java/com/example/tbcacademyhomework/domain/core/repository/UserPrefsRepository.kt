package com.example.tbcacademyhomework.domain.core.repository


interface UserPrefsRepository {
    suspend fun getUserToken(): String?
    suspend fun getUserEmail(): String?
    suspend fun saveToken(token: String)
    suspend fun savEmail(email: String)
    suspend fun saveShouldRemember(shouldRemember: Boolean)
    suspend fun getShouldRemember(): Boolean?
    suspend fun clearData()
}