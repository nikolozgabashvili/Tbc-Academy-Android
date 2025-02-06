package com.example.tbcacademyhomework.presentation.passcode.passcode_button


sealed interface PasscodeButtonType {
    data class Number(val value: String) : PasscodeButtonType
    data object BackSpace : PasscodeButtonType
    data object FingerPrint : PasscodeButtonType

}