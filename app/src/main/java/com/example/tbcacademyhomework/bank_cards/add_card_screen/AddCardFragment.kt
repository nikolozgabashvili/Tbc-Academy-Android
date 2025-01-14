package com.example.tbcacademyhomework.bank_cards.add_card_screen

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.bank_cards.BankCard
import com.example.tbcacademyhomework.bank_cards.BankCardType
import com.example.tbcacademyhomework.bank_cards.cards_pager_screen.CardsPagerFragment.Companion.CARD_KEY
import com.example.tbcacademyhomework.bank_cards.cards_pager_screen.CardsPagerFragment.Companion.REQUEST_KEY
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentAddCardBinding


class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding::inflate) {

    private val viewModel: AddCardViewModel by viewModels()

    private lateinit var navController: NavController

    override fun init(savedInstanceState: Bundle?) {

        navController = findNavController()
        initListeners()
        initViews()


    }

    private fun initListeners() {
        with(binding) {

            rbVisa.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.bankCardView.cardType = BankCardType.VISA
                }
            }
            rbMastercard.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.bankCardView.cardType = BankCardType.MASTERCARD
                }
            }

            btnAddCard.setOnClickListener {
                validateCard()
            }

            btnBack.btnBack.setOnClickListener {
                navController.navigateUp()
            }

            etExpireDate.doOnTextChanged { text, _, _, _ ->
                val input = text.toString().replace("/", "")
                val formatted = when {
                    input.length <= 2 -> input
                    input.length <= 4 -> "${input.substring(0, 2)}/${input.substring(2)}"
                    else -> "${input.substring(0, 2)}/${input.substring(2, 4)}"
                }
                if (formatted != text.toString()) {
                    etExpireDate.setText(formatted)
                    etExpireDate.setSelection(formatted.length)
                }
            }
        }

    }

    private fun initViews() {
        binding.bankCardView.cardType = BankCardType.MASTERCARD
    }

    private fun validateCard() {
        with(binding) {
            if (etExpireDate.text.toString().contains('/')) {
                val card = BankCard(
                    cardHolder = etCardHolder.text.toString(),
                    cardNumber = etCardNumber.text.toString(),
                    expirationYear = etExpireDate.text.toString().split('/')[1],
                    expirationMonth = etExpireDate.text.toString().split('/')[0],
                    cardType = bankCardView.cardType,
                    cvv = etCVV.text.toString()
                )

                if (!viewModel.isValidCard(card)) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.invalid_card_details), Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                val resulBundle = Bundle().apply {
                    putParcelable(
                        CARD_KEY, card
                    )
                }
                setFragmentResult(REQUEST_KEY, resulBundle)
                navController.navigateUp()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.invalid_card_details), Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

}