package com.example.tbcacademyhomework.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.users.repository.OfflineFirstUsersRepository
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.isLoading
import com.example.tbcacademyhomework.presentation.users.util.toUserUi
import com.example.tbcacademyhomework.presentation.utils.toGenericString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val offlineFirstUsersRepository: OfflineFirstUsersRepository
) : ViewModel() {
    private var currentPage = 1
    private val _state = MutableStateFlow(UsersScreenState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<UserScreenEvent>()
    val event = eventChannel.receiveAsFlow()

    init {
        fetchUsers()
        observeRepositoryErrors()
    }

    private fun observeRepositoryErrors() {
        viewModelScope.launch {
            offlineFirstUsersRepository.eventFlow.collect {
                if (it is Resource.Error) {
                    eventChannel.send(UserScreenEvent.Error(it.error.toGenericString()))
                }
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            offlineFirstUsersRepository.fetchUsers(currentPage++).collect { resource ->
                _state.update { it.copy(loading = resource.isLoading()) }

                if (resource is Resource.Success) {
                    _state.update {
                        it.copy(
                            users = resource.data.users.map { user -> user.toUserUi() },
                            pagingFinished = resource.data.endReached,
                        )
                    }
                }

            }
        }
    }


}