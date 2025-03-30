package com.example.tbcacademyhomework.presentation.screen.transaction

sealed interface TransactionScreenAction {
    data class FromAccountSelected(val id: Int) : TransactionScreenAction
    data class ToAccountSelected(val id: Int) : TransactionScreenAction
    data class FromAccountAmountChanged(val amount: Double?) : TransactionScreenAction
    data class ToAccountAmountChanged(val amount: Double?) : TransactionScreenAction
    data object OnContinue : TransactionScreenAction
}