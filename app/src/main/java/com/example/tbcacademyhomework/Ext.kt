package com.example.tbcacademyhomework

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
