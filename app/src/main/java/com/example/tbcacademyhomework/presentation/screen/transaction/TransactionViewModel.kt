package com.example.tbcacademyhomework.presentation.screen.transaction

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.model.transaction.TransferMoneyParamDomain
import com.example.tbcacademyhomework.domain.usecase.GetAccountsUseCase
import com.example.tbcacademyhomework.domain.usecase.GetExchangeRateUseCase
import com.example.tbcacademyhomework.domain.usecase.TransferMoneyUseCase
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseViewModel
import com.example.tbcacademyhomework.presentation.common.util.GenericString
import com.example.tbcacademyhomework.presentation.common.util.toGenericString
import com.example.tbcacademyhomework.presentation.screen.account.model.AccountUi
import com.example.tbcacademyhomework.presentation.screen.account.model.toUiList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transferMoneyUseCase: TransferMoneyUseCase,
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val getAccountsUseCase: GetAccountsUseCase
) : BaseViewModel<TransactionScreenAction, TransactionScreenEvent, TransactionScreenState>(
    TransactionScreenState()
) {

    init {
        getAccounts()

    }

    private fun getAccounts() {
        viewModelScope.launch {
            getAccountsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        updateState { copy(accounts = resource.data.toUiList()) }
                    }

                    is Resource.Error -> {
                        sendEvent(TransactionScreenEvent.Error(resource.error.toGenericString()))
                    }

                    Resource.Loading -> Unit
                }

            }
        }
    }

    override fun onAction(action: TransactionScreenAction) {

        when (action) {
            is TransactionScreenAction.FromAccountSelected -> updateFromAccount(action.id)
            is TransactionScreenAction.ToAccountSelected -> updateToAccount(action.id)
            is TransactionScreenAction.FromAccountAmountChanged -> updateState {
                val conventionRate = state.value.conversionRate
                copy(
                    exchangeFromAmount = action.amount,
                    exchangeToAmount = if (conventionRate != null) action.amount?.times(
                        conventionRate
                    ) else exchangeToAmount
                )
            }

            is TransactionScreenAction.ToAccountAmountChanged -> updateState {
                val conventionRate = state.value.conversionRate
                copy(
                    exchangeToAmount = action.amount,
                    exchangeFromAmount = if (conventionRate != null) action.amount?.div(
                        conventionRate
                    ) else exchangeFromAmount
                )
            }

            TransactionScreenAction.OnContinue -> onContinue()
        }

    }

    private fun onContinue() {
        val fromAccount = state.value.selectedFromAccount
        val toAccount = state.value.selectedToAccount
        val accountBalance = fromAccount?.balance?.toDouble()
        val selectedAmount = state.value.exchangeFromAmount
        if (accountBalance != null && selectedAmount != null && toAccount != null) {
            if (accountBalance < selectedAmount) {
                viewModelScope.launch {
                    sendEvent(TransactionScreenEvent.Error(GenericString.Resource(R.string.not_enough_money)))
                }
            } else {
                transferMoney(fromAccount, toAccount, selectedAmount)
            }
        }
    }

    private fun transferMoney(
        fromAccount: AccountUi,
        toAccount: AccountUi,
        selectedAmount: Double
    ) {
        viewModelScope.launch {
            transferMoneyUseCase(
                TransferMoneyParamDomain(
                    fromAccount = fromAccount.currencyType.name,
                    toAccount = toAccount.currencyType.name,
                    amount = selectedAmount
                )
            ).collect { resource ->
                updateState { copy(processing = resource.isLoading()) }
                when (resource) {
                    is Resource.Error -> sendEvent(TransactionScreenEvent.Error(resource.error.toGenericString()))
                    is Resource.Success -> sendEvent(TransactionScreenEvent.Success)
                    Resource.Loading -> Unit
                }

            }
        }
    }

    private fun updateToAccount(id: Int) {
        updateState {
            copy(toAccountId = id)
        }
        shouldConvert()
    }

    private fun shouldConvert() {
        if (state.value.shouldConvert) {
            val fromCurrency = state.value.selectedFromAccount?.currencyType
            val toCurrency = state.value.selectedToAccount?.currencyType
            if (fromCurrency != null && toCurrency != null)
                viewModelScope.launch {
                    getExchangeRateUseCase(
                        fromAccount = fromCurrency,
                        toAccount = toCurrency
                    ).collect {
                        updateState { copy(processing = it.isLoading()) }
                        if (it is Resource.Success) {
                            updateState { copy(conversionRate = it.data.course) }
                        }
                        if (it is Resource.Error) {
                            sendEvent(TransactionScreenEvent.Error(it.error.toGenericString()))
                        }
                    }
                }
        }

    }

    private fun updateFromAccount(id: Int) {
        updateState {
            copy(fromAccountId = id)

        }
        shouldConvert()
    }


}