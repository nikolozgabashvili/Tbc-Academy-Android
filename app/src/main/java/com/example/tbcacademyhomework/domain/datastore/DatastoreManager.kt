package com.example.tbcacademyhomework.domain.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DatastoreManager {
    suspend fun <T> saveValue(key: Preferences.Key<T>, value: T)
    fun <T> getValue(key: Preferences.Key<T>, defaultValue: T?): Flow<T?>
    suspend fun <T> removeByKey(key: Preferences.Key<T>)
}