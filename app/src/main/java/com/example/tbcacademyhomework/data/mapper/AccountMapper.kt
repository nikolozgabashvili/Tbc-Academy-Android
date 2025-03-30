package com.example.tbcacademyhomework.data.mapper

import com.example.tbcacademyhomework.data.model.account.BankAccountByUserDTO
import com.example.tbcacademyhomework.data.model.account.BankAccountDTO
import com.example.tbcacademyhomework.domain.model.account.BankAccountDomain
import com.example.tbcacademyhomework.domain.model.account.CardType
import com.example.tbcacademyhomework.domain.model.account.CurrencyType

fun BankAccountDTO.toDomain(): BankAccountDomain {
    return BankAccountDomain(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        currencyType = CurrencyType.valueOfOrDefault(currencyType),
        cardType = CardType.getValueOrDefault(cardType),
        balance = balance,
        cardLogo = cardLogo
    )
}

fun List<BankAccountDTO>.toDomainList(): List<BankAccountDomain> {
    return this.map { it.toDomain() }
}

fun List<BankAccountByUserDTO>.getMatchingId(userInfo: String): Int? {
    val id =
        find { it.accountNumber == userInfo || it.phoneNumber == userInfo || it.personalNumber == userInfo }?.accountId
    return id
}

