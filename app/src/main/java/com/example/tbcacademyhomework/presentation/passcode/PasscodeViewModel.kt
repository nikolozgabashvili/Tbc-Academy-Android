package com.example.tbcacademyhomework.presentation.passcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.presentation.passcode.indicator.PasscodeIndicator
import com.example.tbcacademyhomework.presentation.passcode.passcode_button.PasscodeButton
import com.example.tbcacademyhomework.presentation.passcode.passcode_button.PasscodeButtonType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PasscodeViewModel : ViewModel() {

    private val password = "0934"
    private val passwordLayout = listOf(
        PasscodeButton(type = PasscodeButtonType.Number("1")),
        PasscodeButton(type = PasscodeButtonType.Number("2")),
        PasscodeButton(type = PasscodeButtonType.Number("3")),
        PasscodeButton(type = PasscodeButtonType.Number("4")),
        PasscodeButton(type = PasscodeButtonType.Number("5")),
        PasscodeButton(type = PasscodeButtonType.Number("6")),
        PasscodeButton(type = PasscodeButtonType.Number("7")),
        PasscodeButton(type = PasscodeButtonType.Number("8")),
        PasscodeButton(type = PasscodeButtonType.Number("9")),
        PasscodeButton(type = PasscodeButtonType.FingerPrint),
        PasscodeButton(type = PasscodeButtonType.Number("0")),
        PasscodeButton(type = PasscodeButtonType.BackSpace),
    )


    private val _state = MutableStateFlow(PasscodeScreenState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<PasscodeScreenEvent>()
    val event = eventChannel.receiveAsFlow()

    init {
        generatePasswordIndicators()

    }

    private fun generatePasswordIndicators() {
        val indicatorList = password.map {
            PasscodeIndicator()
        }
        viewModelScope.launch {
            _state.update { it.copy(passcodeIndicatorList = indicatorList) }
        }
    }

    fun getPasswordButtons() = passwordLayout

    fun onButtonClicked(type: PasscodeButtonType) {
        when (type) {
            PasscodeButtonType.BackSpace -> {
                _state.update { it.copy(passcode = it.passcode.dropLast(1)) }
            }

            PasscodeButtonType.FingerPrint -> {
                viewModelScope.launch {
                    eventChannel.send(PasscodeScreenEvent.FingerPrint)
                }
            }

            is PasscodeButtonType.Number -> {
                _state.update { it.copy(passcode = it.passcode + type.value) }
                checkPassword()
            }
        }

    }

    private fun checkPassword() {
        val currentPasscode = _state.value.passcode
        if (currentPasscode.length == password.length) {
            viewModelScope.launch {
                if (currentPasscode == password) {
                    eventChannel.send(PasscodeScreenEvent.Success)
                } else {
                    eventChannel.send(PasscodeScreenEvent.Error)
                    clearEnteredPasscode()
                }

            }
        }
    }

    private fun clearEnteredPasscode() {
        _state.update { it.copy(passcode = "") }
    }

}