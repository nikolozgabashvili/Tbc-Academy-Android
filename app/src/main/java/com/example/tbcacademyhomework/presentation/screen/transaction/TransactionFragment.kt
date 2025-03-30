package com.example.tbcacademyhomework.presentation.screen.transaction

import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentTransactionBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.common.extension.loadImage
import com.example.tbcacademyhomework.presentation.common.extension.observeEvent
import com.example.tbcacademyhomework.presentation.common.extension.observeState
import com.example.tbcacademyhomework.presentation.common.extension.showSnackBar
import com.example.tbcacademyhomework.presentation.screen.account.AccountBottomSheetFragment
import com.example.tbcacademyhomework.presentation.screen.receiving_account.ReceivingAccountBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment :
    BaseFragment<FragmentTransactionBinding>(FragmentTransactionBinding::inflate) {

    private val navController by lazy { findNavController() }
    private val viewModel: TransactionViewModel by viewModels()


    override fun init() {
        observe()
        listeners()
        setupResultListener()

    }

    private fun listeners() {
        binding.fromAccount.root.setOnClickListener {
            navController.navigate(TransactionFragmentDirections.actionTransactionFragmentToAccountBottomSheetFragment())
        }

        binding.toAccount.root.setOnClickListener {
            navController.navigate(TransactionFragmentDirections.actionTransactionFragmentToReceivingAccountBottomSheetFragment())
        }

        binding.etSell.doAfterTextChanged {
            viewModel.onAction(
                TransactionScreenAction.FromAccountAmountChanged(
                    it.toString().toDoubleOrNull()
                )
            )
        }

        binding.etSell2.doAfterTextChanged {
            viewModel.onAction(
                TransactionScreenAction.ToAccountAmountChanged(
                    it.toString().toDoubleOrNull()
                )
            )
        }

        binding.btnContinue.setOnClickListener {
            viewModel.onAction(TransactionScreenAction.OnContinue)
        }

    }

    private fun setupResultListener() {
        setFragmentResultListener(AccountBottomSheetFragment.ACCOUNTS_FRAGMENT_RESULT) { _, bundle ->
            val selectedFromAccountId =
                bundle.getInt(AccountBottomSheetFragment.SELECTED_ACCOUNT_ID).takeIf { it != 0 }
            selectedFromAccountId?.let {
                viewModel.onAction(TransactionScreenAction.FromAccountSelected(it))
            }

        }

        setFragmentResultListener(ReceivingAccountBottomSheetFragment.RECEIVING_ACCOUNT_FRAGMENT_RESULT) { _, bundle ->
            val selectedFromAccountId =
                bundle.getInt(ReceivingAccountBottomSheetFragment.RECEIVING_ACCOUNT_ID)
                    .takeIf { it != 0 }
            selectedFromAccountId?.let {
                viewModel.onAction(TransactionScreenAction.ToAccountSelected(it))
            }

        }
    }

    private fun observe() {
        observeState(viewModel.state) { state ->
            handleFromAccountItem(state)
            handleToAccountItem(state)
            handleInputVisibility(state.shouldConvert)
            handleInputs(state)
            handleLoading(state.processing)
            handleButton(state.continueEnabled, state.processing)
            handleExchangeRates(state)
        }

        observeEvent(viewModel.event) {
            when (it) {
                is TransactionScreenEvent.Error -> binding.root.showSnackBar(it.error)
                TransactionScreenEvent.Success -> binding.root.showSnackBar(R.string.transfer_success)
            }
        }
    }

    private fun handleExchangeRates(state: TransactionScreenState) {
        binding.tvExchangeRate.isVisible = state.shouldConvert
        binding.tvExchangeRate.text = getString(
            R.string.exchange_tooltip,
            state.selectedFromAccount?.currencyType?.displayName,
            state.conversionRate.toString(),
            state.selectedToAccount?.currencyType?.displayName
        )

    }

    private fun handleButton(continueEnabled: Boolean, loading: Boolean) {
        binding.btnContinue.isEnabled = continueEnabled && !loading
    }

    private fun handleInputs(state: TransactionScreenState) {
        with(binding) {
            if (etSell2.text.toString().toDoubleOrNull() != state.exchangeToAmount) {
                etSell2.setText(state.exchangeToAmount?.toString())

            }
            if (etSell.text.toString().toDoubleOrNull() != state.exchangeFromAmount) {
                etSell.setText(state.exchangeFromAmount?.toString())
            }
            etLayoutSell.suffixText = state.selectedFromAccount?.currencyType?.displayName
            etLayoutSell2.suffixText = state.selectedToAccount?.currencyType?.displayName
        }


    }

    private fun handleLoading(loading: Boolean) {
        with(binding) {
            progressLoader.isVisible = loading
            fromAccount.root.isEnabled = !loading
            toAccount.root.isEnabled = !loading
            etLayoutSell.isEnabled = !loading
            etLayoutSell2.isEnabled = !loading
            etLayoutDesc.isEnabled = !loading

        }

    }

    private fun handleInputVisibility(shouldConvert: Boolean) {
        binding.etLayoutSell2.isVisible = shouldConvert
    }

    private fun handleFromAccountItem(state: TransactionScreenState) {
        with(binding.fromAccount) {
            ivAccount.isVisible = state.selectedFromAccount != null
            tvAccountBalance.isVisible = state.selectedFromAccount != null
            tvAccountName.isVisible = state.selectedFromAccount != null
            tvAccountCardNum.isVisible = state.selectedFromAccount != null
            tvEmptyItem.isVisible = state.selectedFromAccount == null
            state.selectedFromAccount?.let {
                ivAccount.loadImage(it.cardLogo)
                tvAccountName.text = it.accountName
                tvAccountBalance.text = getString(
                    R.string.formatted_balance,
                    it.balance.toString(),
                    it.currencyType.displayName
                )
                tvAccountCardNum.text = root.context.getString(
                    R.string.formatted_card_number,
                    it.accountNumber.takeLast(4)
                )

            }
        }
        binding.etLayoutSell.isEnabled = state.sellInputEnabled

    }

    private fun handleToAccountItem(state: TransactionScreenState) {
        with(binding.toAccount) {
            ivAccount.isVisible = state.selectedToAccount != null
            tvAccountBalance.isVisible = state.selectedToAccount != null
            tvAccountName.isVisible = state.selectedToAccount != null
            tvAccountCardNum.isVisible = state.selectedToAccount != null
            tvEmptyItem.isVisible = state.selectedToAccount == null
            state.selectedToAccount?.let {
                ivAccount.loadImage(it.cardLogo)
                tvAccountName.text = it.accountName
                tvAccountBalance.text = getString(
                    R.string.formatted_balance,
                    it.balance.toString(),
                    it.currencyType.displayName
                )
                tvAccountCardNum.text = root.context.getString(
                    R.string.formatted_card_number,
                    it.accountNumber.takeLast(4)
                )

            }

        }
        binding.etLayoutSell2.isEnabled = state.sellInput2Enabled
    }


}