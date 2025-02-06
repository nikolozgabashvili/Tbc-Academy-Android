package com.example.tbcacademyhomework.presentation.passcode.passcode_button

import java.util.UUID

data class PasscodeButton(
    val id: UUID = UUID.randomUUID(),
    val type: PasscodeButtonType,
)
