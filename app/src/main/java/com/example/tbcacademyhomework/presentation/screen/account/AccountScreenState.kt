package com.example.tbcacademyhomework.presentation.screen.account

import com.example.tbcacademyhomework.presentation.screen.account.model.AccountUi

data class AccountScreenState(
    val isLoading: Boolean = false,
    val accounts: List<AccountUi> = emptyList()
)
