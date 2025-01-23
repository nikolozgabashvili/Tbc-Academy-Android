package com.example.tbcacademyhomework.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreImpl(
    private val context: Context
) {

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    fun getMail():Flow<String?>{
        return context.dataStore.data.map { preferences ->
            preferences[EMAIL_KEY]
        }
    }

    suspend fun clearMemory() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(EMAIL_KEY)

        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")
    }


}