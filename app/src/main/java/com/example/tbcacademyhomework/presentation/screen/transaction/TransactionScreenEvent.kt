package com.example.tbcacademyhomework.presentation.screen.transaction

import com.example.tbcacademyhomework.presentation.common.util.GenericString

sealed interface TransactionScreenEvent {
    data class Error(val error: GenericString):TransactionScreenEvent
    data object Success:TransactionScreenEvent

}