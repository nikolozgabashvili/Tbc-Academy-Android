package com.example.tbcacademyhomework.presentation.screen.receiving_account

import com.example.tbcacademyhomework.presentation.common.util.GenericString

sealed interface ReceivingAccountScreenEvent {
    data class Error(val error:GenericString):ReceivingAccountScreenEvent
    data class Success(val id:Int):ReceivingAccountScreenEvent
}