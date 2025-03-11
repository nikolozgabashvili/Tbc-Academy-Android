package com.example.tbcacademyhomework.domain.datastore.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.tbcacademyhomework.domain.datastore.DatastoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetValueUseCase {
    operator fun <T> invoke(key: Preferences.Key<T>, defaultValue: T?): Flow<T?>
}

class GetValueUseCaseImpl @Inject constructor(
    private val datastoreManager: DatastoreManager
) : GetValueUseCase {
    override fun <T> invoke(key: Preferences.Key<T>, defaultValue: T?): Flow<T?> {
        return datastoreManager.getValue(key, defaultValue)
    }

}