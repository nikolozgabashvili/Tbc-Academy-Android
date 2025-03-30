package com.example.tbcacademyhomework.presentation.screen.account.model

import com.example.tbcacademyhomework.domain.model.account.CardType
import com.example.tbcacademyhomework.domain.model.account.CurrencyType

data class AccountUi(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val currencyType: CurrencyType,
    val cardType: CardType,
    val balance: Int,
    val cardLogo: String?
)