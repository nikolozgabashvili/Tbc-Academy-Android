package com.example.tbcacademyhomework.domain.datastore.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.tbcacademyhomework.domain.datastore.DatastoreManager
import javax.inject.Inject

interface RemoveByKeyUseCase {
    suspend operator fun <T> invoke(key: Preferences.Key<T>)
}

class RemoveByKeyUseCaseImpl @Inject constructor(
    private val datastoreManager: DatastoreManager
) : RemoveByKeyUseCase {
    override suspend fun <T> invoke(key: Preferences.Key<T>) {
        datastoreManager.removeByKey(key)
    }
}