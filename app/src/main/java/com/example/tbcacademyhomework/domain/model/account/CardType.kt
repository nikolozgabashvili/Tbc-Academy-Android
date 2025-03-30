package com.example.tbcacademyhomework.domain.model.account

enum class CardType {
    MASTER_CARD,
    VISA;

    companion object {
        fun getValueOrDefault(name: String): CardType {
            return when (name) {
                "VISA" -> VISA
                "MASTER_CARD" -> MASTER_CARD
                else -> VISA
            }
        }
    }
}
