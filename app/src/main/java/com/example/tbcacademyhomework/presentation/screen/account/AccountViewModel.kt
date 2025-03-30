package com.example.tbcacademyhomework.presentation.screen.account

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.usecase.GetAccountsUseCase
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseViewModel
import com.example.tbcacademyhomework.presentation.common.util.toGenericString
import com.example.tbcacademyhomework.presentation.screen.account.model.toUiList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
) : BaseViewModel<Nothing, AccountScreenEvent, AccountScreenState>(AccountScreenState()) {

    init {
        getAccounts()
    }

    private fun getAccounts() {
        viewModelScope.launch {
            getAccountsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }
                if (resource is Resource.Success) {
                    updateState { copy(accounts = resource.data.toUiList()) }
                }

                if (resource is Resource.Error) {
                    sendEvent(AccountScreenEvent.Error(resource.error.toGenericString()))
                }

            }
        }
    }

    override fun onAction(action: Nothing) {}

}