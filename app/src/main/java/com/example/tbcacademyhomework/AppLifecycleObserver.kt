package com.example.tbcacademyhomework

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.tbcacademyhomework.domain.core.repository.UserPrefsRepository
import com.example.tbcacademyhomework.domain.users.repository.UsersLocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppLifecycleObserver @Inject constructor(
    private val localDataSource: UsersLocalRepository,
    private val userPrefsRepository: UserPrefsRepository,
) : DefaultLifecycleObserver {

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        clearDataStore()
    }

    private fun clearDataStore() {
        CoroutineScope(Dispatchers.IO).launch {
            val shouldRemember = userPrefsRepository.getShouldRemember()
            if (shouldRemember != true) {
                localDataSource.deleteData()
                userPrefsRepository.clearData()
            }
        }
    }
}