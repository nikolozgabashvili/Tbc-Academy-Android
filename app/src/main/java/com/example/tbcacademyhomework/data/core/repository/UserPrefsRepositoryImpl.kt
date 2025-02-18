package com.example.tbcacademyhomework.data.core.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbcacademyhomework.domain.core.repository.UserPrefsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserPrefsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : UserPrefsRepository {
    override suspend fun getUserToken(): String? {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences ->
                preferences[TOKEN_KEY]
            }.first()

        }
    }

    override suspend fun getUserEmail(): Flow<String?> {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences ->
                preferences[EMAIL_KEY]
            }
        }
    }

    override suspend fun saveToken(token: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[TOKEN_KEY] = token
            }
        }

    }

    override suspend fun savEmail(email: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[EMAIL_KEY] = email
            }
        }
    }

    override suspend fun saveShouldRemember(shouldRemember: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[SHOULD_REMEMBER] = shouldRemember
            }
        }
    }

    override suspend fun getShouldRemember(): Boolean? {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences ->
                preferences[SHOULD_REMEMBER]
            }.first()
        }
    }

    override suspend fun clearData() {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences.remove(TOKEN_KEY)
                preferences.remove(EMAIL_KEY)
                preferences.remove(SHOULD_REMEMBER)

            }
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val SHOULD_REMEMBER = booleanPreferencesKey("remember_user")
    }


}