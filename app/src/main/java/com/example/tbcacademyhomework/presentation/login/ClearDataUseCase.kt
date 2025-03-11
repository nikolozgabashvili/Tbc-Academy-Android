package com.example.tbcacademyhomework.presentation.login

import com.example.tbcacademyhomework.domain.datastore.DatastorePreferenceKeys
import com.example.tbcacademyhomework.domain.datastore.usecase.RemoveByKeyUseCase
import com.example.tbcacademyhomework.domain.users.usecase.ClearLocalDataUseCase
import javax.inject.Inject

interface ClearDataUseCase {
    suspend operator fun invoke()
}

class ClearDataUseCaseImpl @Inject constructor(
    private val removeByKeyUseCase: RemoveByKeyUseCase,
    private val clearLocalDataUseCase: ClearLocalDataUseCase
) : ClearDataUseCase {
    override suspend fun invoke() {
        removeByKeyUseCase(DatastorePreferenceKeys.EMAIL)
        removeByKeyUseCase(DatastorePreferenceKeys.TOKEN)
        removeByKeyUseCase(DatastorePreferenceKeys.SHOULD_REMEMBER)
        clearLocalDataUseCase()
    }

}