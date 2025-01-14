package com.example.tbcacademyhomework.bank_cards

import androidx.annotation.DrawableRes
import com.example.tbcacademyhomework.R

enum class BankCardType(
    @DrawableRes
    val backgroundColor: Int,
    @DrawableRes
    val cardIcon: Int
) {
    VISA(
        backgroundColor = R.drawable.bg_visa,
        cardIcon = R.drawable.ic_visa
    ),
    MASTERCARD(
        backgroundColor = R.drawable.bg_mastercard,
        cardIcon = R.drawable.img_mastercard
    )
}