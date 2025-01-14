package com.example.tbcacademyhomework.bank_cards.add_card_screen

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.bank_cards.BankCard
import java.util.Calendar
import java.util.Locale

class AddCardViewModel : ViewModel() {

    private val calendar = Calendar.getInstance(Locale.getDefault())

    private val month = calendar.get(Calendar.MONTH) + 1
    private val year = calendar.get(Calendar.YEAR) % 100

    fun isValidCard(card: BankCard): Boolean {
        return with(card) {
            cardHolder.isNotBlank() &&
                    cardNumber.length == 16 && cvv.length == 3 &&
                    expirationYear.toInt() >= year && expirationMonth.toInt() >= month &&
                    expirationMonth.toInt() <= 12
        }

    }

}