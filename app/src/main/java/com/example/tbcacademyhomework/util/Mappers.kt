package com.example.tbcacademyhomework.util

import com.example.tbcacademyhomework.models.KeyboardType
import com.example.tbcacademyhomework.models.ResponseDataDto
import com.example.tbcacademyhomework.models.ResponseDataUi
import com.example.tbcacademyhomework.models.FieldType

fun ResponseDataDto.toResponseDataUi(): ResponseDataUi {
    val kType = KeyboardType.getType(keyboard)
    val fType = FieldType.getType(fieldType)

    return ResponseDataUi(
        id = fieldId,
        hint = hint,
        fieldType = fType,
        keyboardType = kType,
        required = required,
        isActive = isActive,
        icon = icon

    )
}

fun List<List<ResponseDataDto>>.toResponseDataUiList(): List<List<ResponseDataUi>> {
    return this.map { it.map { response -> response.toResponseDataUi() } }
}
