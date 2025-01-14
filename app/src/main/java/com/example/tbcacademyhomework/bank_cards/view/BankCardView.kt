package com.example.tbcacademyhomework.bank_cards.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.bank_cards.BankCardType
import com.example.tbcacademyhomework.databinding.ViewBankCardBinding

class BankCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = ViewBankCardBinding.inflate(LayoutInflater.from(context), this)

    init {
        setDefaultAppearance()
    }


    var cardType: BankCardType = BankCardType.MASTERCARD
        set(value) {
            field = value
            setCardBackground()
        }


    var cardNumber: String? = null
        set(value) {
            field = value
            setCardNumber()
        }

    var cardHolderName: String? = null
        set(value) {
            field = value
            setCardHolderName()
        }

    var expirationDate: String? = null
        set(value) {
            field = value
            setExpirationDate()
        }

    private fun setDefaultAppearance() {
        cardType = BankCardType.MASTERCARD
        cardNumber = null
        cardHolderName = null
        expirationDate = null
    }

    private fun setCardBackground() {
        with(binding) {
            container.setBackgroundResource(cardType.backgroundColor)
            ivCardIcon.setImageResource(cardType.cardIcon)
        }


    }


    private fun setCardNumber() {
        with(binding) {
            cardNumber?.let { number ->
                if (number.length == 16) {
                    tvCardNumber1.text = number.substring(0, 4)
                    tvCardNumber2.text = number.substring(4, 8)
                    tvCardNumber3.text = number.substring(8, 12)
                    tvCardNumber4.text = number.substring(12, 16)

                }

            } ?: run {
                tvCardNumber1.text = context.getString(R.string.card_number_placeholder)
                tvCardNumber2.text = context.getString(R.string.card_number_placeholder)
                tvCardNumber3.text = context.getString(R.string.card_number_placeholder)
                tvCardNumber4.text = context.getString(R.string.card_number_placeholder)
            }


        }


    }

    private fun setCardHolderName() {
        cardHolderName?.let {
            binding.tvCardHolderName.text = it
        } ?: run {
            binding.tvCardHolderName.text = context.getString(R.string.cardholder_placeholder)
        }

    }

    private fun setExpirationDate() {
        expirationDate?.let {
            binding.tvValidDate.text = it
        } ?: run {
            binding.tvValidDate.text = context.getString(R.string.date_placeholder)
        }
    }
}

