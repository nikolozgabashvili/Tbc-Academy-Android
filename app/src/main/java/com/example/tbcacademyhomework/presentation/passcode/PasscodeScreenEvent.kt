package com.example.tbcacademyhomework.presentation.passcode

sealed interface PasscodeScreenEvent {
    data object Success : PasscodeScreenEvent
    data object Error : PasscodeScreenEvent
    data object FingerPrint : PasscodeScreenEvent
}