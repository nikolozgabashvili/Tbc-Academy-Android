package com.example.tbcacademyhomework.presentation.screen.account.model

import com.example.tbcacademyhomework.domain.model.account.BankAccountDomain

fun BankAccountDomain.toUi(): AccountUi {
    return AccountUi(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        currencyType = currencyType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )

}

fun List<BankAccountDomain>.toUiList(): List<AccountUi> {
    return this.map { it.toUi() }
}