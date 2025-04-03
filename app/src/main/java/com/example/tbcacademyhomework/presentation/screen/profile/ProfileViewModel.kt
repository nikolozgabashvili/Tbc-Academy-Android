package com.example.tbcacademyhomework.presentation.screen.profile

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.common.usecase.ClearDataUseCase
import com.example.tbcacademyhomework.domain.datastore.DatastorePreferenceKeys
import com.example.tbcacademyhomework.domain.datastore.usecase.GetValueUseCase
import com.example.tbcacademyhomework.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getValueUseCase: GetValueUseCase,
    private val clearDataUseCase: ClearDataUseCase
) : BaseViewModel<ProfileScreenState, ProfileScreenAction, ProfileScreenEvent>(ProfileScreenState()) {

    init {
        viewModelScope.launch {
            getValueUseCase(DatastorePreferenceKeys.EMAIL, null).first()?.let { email ->
                updateState { copy(email = email) }
            }

        }

    }

    override fun onAction(action: ProfileScreenAction) {
        when (action) {
            ProfileScreenAction.OnLogout -> logOutUser()
        }

    }


    private fun logOutUser() {
        viewModelScope.launch {
            clearDataUseCase()
            sendEvent(ProfileScreenEvent.Success)
        }

    }



}