package com.example.tbcacademyhomework.messages

import com.example.tbcacademyhomework.util.DateTimeFormatter

class MessagesDatabase {

    private val dateTimeFormatter = DateTimeFormatter()

    private val messages: MutableList<Message> = mutableListOf()

    fun addMessage(msg: String) {
        val timeStamp = System.currentTimeMillis()
        val message = Message(
            message = msg,
            timeStamp = dateTimeFormatter.formatTime(timeStamp),
            messageType = if (messages.size % 2 == 0) MessageType.OTHER else MessageType.SELF
        )
        messages.add(0, message)
    }

    fun getMessages() = messages.toList()
}