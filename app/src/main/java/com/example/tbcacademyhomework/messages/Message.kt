package com.example.tbcacademyhomework.messages

import java.util.UUID

data class Message(
    val id: UUID = UUID.randomUUID(),
    val message: String,
    val messageType: MessageType,
    val timeStamp: String,
)
