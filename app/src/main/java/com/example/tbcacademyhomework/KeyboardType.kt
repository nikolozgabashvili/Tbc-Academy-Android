package com.example.tbcacademyhomework



enum class KeyboardType(val type: String) {
    TEXT("text"),
    NUMBER("number");

    companion object {
        fun getType(type: String?): KeyboardType? {
            return entries.find { it.type == type }
        }
    }

}