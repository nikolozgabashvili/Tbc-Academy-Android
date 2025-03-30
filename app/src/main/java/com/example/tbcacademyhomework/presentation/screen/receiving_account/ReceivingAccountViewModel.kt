package com.example.tbcacademyhomework.presentation.screen.receiving_account

import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.domain.usecase.GetAccountByUserInfoUseCase
import com.example.tbcacademyhomework.domain.usecase.ValidateUserInfoUseCase
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseViewModel
import com.example.tbcacademyhomework.presentation.common.util.GenericString
import com.example.tbcacademyhomework.presentation.common.util.toGenericString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceivingAccountViewModel @Inject constructor(
    private val validateUserInfoUseCase: ValidateUserInfoUseCase,
    private val getAccountByUserInfoUseCase: GetAccountByUserInfoUseCase
) : BaseViewModel<ReceivingAccountScreenAction, ReceivingAccountScreenEvent, ReceivingAccountScreenState>(
    ReceivingAccountScreenState()
) {

    override fun onAction(action: ReceivingAccountScreenAction) {
        when (action) {
            is ReceivingAccountScreenAction.OnConfirm -> {
                validateInput(action.info)
            }
        }
    }

    private fun validateInput(info: String) {
        viewModelScope.launch {
            val isValid = validateUserInfoUseCase(info)
            if (isValid) {
                getAccount(info)
            }else{
                sendEvent(ReceivingAccountScreenEvent.Error(GenericString.Resource(R.string.invalid_input)))
            }
        }
    }

    private fun getAccount(info: String) {
        viewModelScope.launch {
            getAccountByUserInfoUseCase(info).collect {
                updateState { copy(loading = it.isLoading()) }
                when (it) {
                    is Resource.Error -> {
                        sendEvent(ReceivingAccountScreenEvent.Error(it.error.toGenericString()))
                    }

                    is Resource.Success -> {
                        onSuccess(it.data)
                    }

                    Resource.Loading -> Unit
                }
            }
        }
    }

    private suspend fun onSuccess(id: Int?) {
        if (id != null) sendEvent(ReceivingAccountScreenEvent.Success(id))
        else sendEvent(ReceivingAccountScreenEvent.Error(GenericString.Resource(R.string.account_was_not_found)))

    }
}