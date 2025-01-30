package com.example.tbcacademyhomework.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.error.UserValidationError
import com.example.tbcacademyhomework.user.UserDataStoreImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userDataStore: UserDataStoreImpl) : ViewModel() {

    private val _events = Channel<UserFragmentEvents>()
    val events = _events.receiveAsFlow()

    private val _fetchedUser = MutableStateFlow<UserUi?>(null)
    val fetchedUser = _fetchedUser.asStateFlow()


    private fun validateUser(user: UserUi): Boolean {

        var isValid = true
        viewModelScope.launch {
            if (!isValidEmail(user.email)) {
                _events.send(
                    UserFragmentEvents.Error(UserValidationError.INVALID_EMAIL)
                )
                isValid = false
            }
            if (user.firstName.isEmpty()) {
                _events.send(
                    UserFragmentEvents.Error(UserValidationError.EMPTY_FIRST_NAME)
                )
                isValid = false
            }
            if (user.lastName.isEmpty()) {
                _events.send(
                    UserFragmentEvents.Error(UserValidationError.EMPTY_LAST_NAME)
                )
                isValid = false
            }
        }

        return isValid

    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userDataStore.getUser().also {
                _fetchedUser.emit(it.toUserUi())
            }
        }
    }


    fun saveUser(user: UserUi) {
        val isUserInputValid = validateUser(user)
        if (isUserInputValid) {
            viewModelScope.launch(Dispatchers.IO) {
                userDataStore.saveUser(user.toUser())
                _events.send(UserFragmentEvents.Success)

            }
        }

    }


}