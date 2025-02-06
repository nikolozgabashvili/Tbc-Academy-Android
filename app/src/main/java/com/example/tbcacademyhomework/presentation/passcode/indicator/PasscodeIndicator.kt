package com.example.tbcacademyhomework.presentation.passcode.indicator

import java.util.UUID

data class PasscodeIndicator(
    val id: UUID = UUID.randomUUID(),
    val isSelected: Boolean = false,
)
