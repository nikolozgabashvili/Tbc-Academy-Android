package com.example.tbcacademyhomework

enum class MessageType(val type: String) {
    TEXT("text"),
    VOICE("voice"),
    File("file");

    companion object {
        fun getFromType(type: String) = entries.find { it.type == type } ?: TEXT
    }
}