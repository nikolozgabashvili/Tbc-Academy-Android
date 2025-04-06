package com.example.tbcacademyhomework.presentation.screen.users

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.data.users.repository.GetPagedUsersUseCase
import com.example.tbcacademyhomework.domain.utils.Resource
import com.example.tbcacademyhomework.domain.utils.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseViewModel
import com.example.tbcacademyhomework.presentation.screen.users.util.toUserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getPagedUsersUseCase: GetPagedUsersUseCase
) : BaseViewModel<UsersScreenState, UserScreenAction, UserScreenEvent>(UsersScreenState()) {
    private var currentPage = 1



    override fun onAction(action: UserScreenAction) {
        when (action) {
            UserScreenAction.OnFetchRequest -> fetchUsers()
        }

    }


    private fun fetchUsers() {
        viewModelScope.launch {
            updateState { copy(loading = true) }
            getPagedUsersUseCase(currentPage++).collect { resource ->
                updateState { copy(loading = resource.isLoading()) }

                if (resource is Resource.Success) {
                    updateState {
                        copy(
                            users = resource.data.users.map { user -> user.toUserUi() },
                            pagingFinished = resource.data.endReached,
                        )
                    }
                }

            }
        }
    }


}