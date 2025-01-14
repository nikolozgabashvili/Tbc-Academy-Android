package com.example.tbcacademyhomework.bank_cards.cards_pager_screen

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.bank_cards.BankCard

class CardsPagerViewModel : ViewModel() {


    private val cardsList = mutableListOf<BankCard>()

    fun addCard(card: BankCard) {
        cardsList.add(0, card)
    }

    fun removeCard(cardId: String) {
        cardsList.removeIf { it.id == cardId }
    }

    fun getCards() = cardsList.toList()
}