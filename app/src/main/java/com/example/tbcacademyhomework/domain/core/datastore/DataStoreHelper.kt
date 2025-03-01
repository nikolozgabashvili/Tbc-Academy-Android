package com.example.tbcacademyhomework.domain.core.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreHelper {
    suspend fun <T> getValue(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> setValue(key: Preferences.Key<T>, value: T)
}