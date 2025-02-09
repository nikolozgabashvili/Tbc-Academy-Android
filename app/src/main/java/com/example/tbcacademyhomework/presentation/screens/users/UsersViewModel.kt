package com.example.tbcacademyhomework.presentation.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.tbcacademyhomework.domain.users.repository.UsersRepository
import com.example.tbcacademyhomework.presentation.screens.users.models.toUserUi
import kotlinx.coroutines.flow.map

class UsersViewModel(
    usersRepository: UsersRepository
) : ViewModel() {

    val users = usersRepository.getUsers().map { it.map { it.toUserUi() } }
        .cachedIn(viewModelScope)

}