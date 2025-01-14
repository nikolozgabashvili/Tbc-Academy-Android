package com.example.tbcacademyhomework.bank_cards


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BankCard(
    val id: String = UUID.randomUUID().toString(),
    val cardHolder: String,
    val cardNumber: String,
    val cardType: BankCardType,
    val expirationYear: String,
    val expirationMonth: String,
    val cvv: String
) : Parcelable
