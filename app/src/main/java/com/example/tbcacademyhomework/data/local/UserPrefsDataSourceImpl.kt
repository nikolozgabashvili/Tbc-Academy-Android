package com.example.tbcacademyhomework.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tbcacademyhomework.domain.local.UserPrefsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserPrefsDataSourceImpl(
    private val context: Context
) : UserPrefsDataSource {
    override suspend fun getUserToken(): Flow<String?> {
        return withContext(Dispatchers.IO) {
            context.dataStore.data.map { preferences ->
                preferences[TOKEN_KEY]
            }

        }
    }

    override suspend fun getUserEmail(): Flow<String?> {
        return withContext(Dispatchers.IO) {
            context.dataStore.data.map { preferences ->
                preferences[EMAIL_KEY]
            }
        }
    }

    override suspend fun saveToken(token: String) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[TOKEN_KEY] = token
            }
        }

    }

    override suspend fun savEmail(email: String) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[EMAIL_KEY] = email
            }
        }
    }

    override suspend fun clearData() {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences.remove(TOKEN_KEY)
                preferences.remove(EMAIL_KEY)

            }
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")
    }


}