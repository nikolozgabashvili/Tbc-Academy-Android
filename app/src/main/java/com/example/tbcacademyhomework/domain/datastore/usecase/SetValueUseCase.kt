package com.example.tbcacademyhomework.domain.datastore.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.tbcacademyhomework.domain.datastore.DatastoreManager
import javax.inject.Inject

interface SetValueUseCase {
    suspend operator fun <T> invoke(key: Preferences.Key<T>, value: T)
}

class SetValueUseCaseImpl @Inject constructor(
    private val datastoreManager: DatastoreManager
) : SetValueUseCase {
    override suspend fun <T> invoke(key: Preferences.Key<T>, value: T) {
        datastoreManager.saveValue(key, value)
    }


}