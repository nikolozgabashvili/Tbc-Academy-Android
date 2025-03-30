package com.example.tbcacademyhomework.presentation.screen.transaction

import com.example.tbcacademyhomework.presentation.screen.account.model.AccountUi

data class TransactionScreenState(
    val fromAccountId: Int? = null,
    val toAccountId: Int? = null,
    val accounts: List<AccountUi> = emptyList(),
    val processing: Boolean = false,
    val exchangeFromAmount: Double? = null,
    val exchangeToAmount: Double? = null,
    val conversionRate: Double? = null
) {
    val selectedFromAccount: AccountUi?
        get() = accounts.find { it.id == fromAccountId }

    val selectedToAccount: AccountUi?
        get() = accounts.find { it.id == toAccountId }

    val shouldConvert: Boolean
        get() = selectedFromAccount?.currencyType != selectedToAccount?.currencyType && selectedFromAccount != null && selectedToAccount != null

    val sellInputEnabled: Boolean
        get() = selectedFromAccount != null
    val sellInput2Enabled: Boolean
        get() = selectedToAccount != null

    val continueEnabled: Boolean
        get() = selectedFromAccount != null && selectedToAccount != null && exchangeFromAmount != null


}
