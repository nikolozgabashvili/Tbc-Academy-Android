package com.example.tbcacademyhomework.presentation.core.views.input

enum class InputType(val value: Int) {
    STANDARD(1),
    PASSWORD(2);

    companion object {
        fun getEnumWithValue(value: Int): InputType {
            return entries.firstOrNull { it.value == value } ?: STANDARD
        }
    }
}