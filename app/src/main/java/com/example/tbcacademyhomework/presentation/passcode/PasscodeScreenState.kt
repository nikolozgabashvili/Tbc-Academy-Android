package com.example.tbcacademyhomework.presentation.passcode

import com.example.tbcacademyhomework.presentation.passcode.indicator.PasscodeIndicator

data class PasscodeScreenState(
    val passcode: String = "",
    private val passcodeIndicatorList: List<PasscodeIndicator> = emptyList()
) {
    val indicators: List<PasscodeIndicator>
        get() =  passcodeIndicatorList.map {
                it.copy(
                    isSelected = passcode.length > passcodeIndicatorList.indexOf(
                        it
                    )
                )
            }

}