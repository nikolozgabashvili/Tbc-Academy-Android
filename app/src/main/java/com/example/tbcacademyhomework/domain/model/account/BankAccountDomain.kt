package com.example.tbcacademyhomework.domain.model.account

data class BankAccountDomain(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val currencyType: CurrencyType,
    val cardType: CardType,
    val balance: Int,
    val cardLogo: String?
)
