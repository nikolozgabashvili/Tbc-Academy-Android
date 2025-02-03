package com.example.tbcacademyhomework.presentation.user


import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.base.BaseViewModel
import com.example.tbcacademyhomework.data.local.UsersDatabase
import com.example.tbcacademyhomework.data.local.entity.UserEntity
import com.example.tbcacademyhomework.data.remote.RetrofitImpl
import com.example.tbcacademyhomework.util.NetworkManager
import com.example.tbcacademyhomework.util.isError
import com.example.tbcacademyhomework.util.isLoading
import com.example.tbcacademyhomework.util.isSuccess
import com.example.tbcacademyhomework.util.toUserEntity
import com.example.tbcacademyhomework.util.toUserUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(
    private val usersDatabase: UsersDatabase,
    private val networkManager: NetworkManager
) : BaseViewModel() {

    private val _state = MutableStateFlow(UsersScreenState())
    val state = _state.asStateFlow()

    private val _event = Channel<UserEvents>()
    val event = _event.receiveAsFlow()

    init {
        fetchUsers()
    }

    private fun getUsersFromRemote() {
        viewModelScope.launch(Dispatchers.IO) {
            if (networkManager.isInternetAvailable()) {
                withContext(Dispatchers.Main.immediate) {
                    _event.send(UserEvents.YesNetwork)
                }
                execute {
                    RetrofitImpl.apiService.getUsers()
                }.collect { resource ->
                    _state.update { it.copy(isFetching = resource.isLoading()) }
                    if (resource.isSuccess()) {
                        val users = resource.data?.users
                        users?.let {
                            saveUsersLocally(it.map { it.toUserEntity() })
                        }

                    } else if (resource.isError()) {
                        withContext(Dispatchers.Main.immediate) {
                            _event.send(UserEvents.Error(resource))
                        }
                    }
                }
            } else {
                withContext(Dispatchers.Main.immediate) {
                    _event.send(UserEvents.NoNetwork)
                }
            }
        }

    }


    private fun fetchUsers() {
        getUsersFromLocal()
        getUsersFromRemote()
    }


    private fun getUsersFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            usersDatabase.userDao().getUsers().collect { users ->
                _state.update { it.copy(users = users.map { it.toUserUi() }) }
            }
        }

    }

    private fun saveUsersLocally(users: List<UserEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            usersDatabase.userDao().upsertUsers(users)
        }
    }


}