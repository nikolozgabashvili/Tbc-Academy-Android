package com.example.tbcacademyhomework.domain.model.account

import com.example.tbcacademyhomework.presentation.common.Consts

enum class CurrencyType(val displayName: String) {
    GEL(Consts.SYMBOL_GEL),
    USD(Consts.SYMBOL_USD),
    EUR(Consts.SYMBOL_EUR);

    companion object {
        fun valueOfOrDefault(name: String): CurrencyType {
            return when (name) {
                "GEL" -> GEL
                "EUR" -> EUR
                "USD" -> USD
                else -> GEL
            }
        }
    }

}