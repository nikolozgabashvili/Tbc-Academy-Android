package com.example.tbcacademyhomework.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.tbcacademyhomework.domain.datastore.DatastoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatastoreManagerImpl @Inject constructor(
    private val datastore: DataStore<Preferences>
) : DatastoreManager {
    override suspend fun <T> saveValue(key: Preferences.Key<T>, value: T) {
        datastore.edit {
            it[key] = value
        }
    }

    override fun <T> getValue(key: Preferences.Key<T>, defaultValue: T?): Flow<T?> {
        return datastore.data.map {
            it[key] ?: defaultValue
        }
    }


    override suspend fun <T> removeByKey(key: Preferences.Key<T>) {
        datastore.edit {
            it.remove(key)
        }
    }
}