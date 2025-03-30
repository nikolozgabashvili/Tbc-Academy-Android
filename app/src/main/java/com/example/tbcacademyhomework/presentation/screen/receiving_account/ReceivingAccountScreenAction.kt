package com.example.tbcacademyhomework.presentation.screen.receiving_account

sealed interface ReceivingAccountScreenAction {
    data class OnConfirm(val info: String) : ReceivingAccountScreenAction
}