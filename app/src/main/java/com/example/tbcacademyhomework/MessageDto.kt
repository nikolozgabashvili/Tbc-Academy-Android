package com.example.tbcacademyhomework

import com.squareup.moshi.Json

data class MessageDto(
    val id: Int,
    val image: String?,
    val owner: String,
    @Json(name = "last_message")
    val lastMessage: String,
    @Json(name = "last_active")
    val lastActive: String,
    @Json(name = "unread_messages")
    val unreadMessages: Int,
    @Json(name = "is_typing")
    val isTyping: Boolean,
    @Json(name = "laste_message_type")
    val lastMessageType: String

)

fun MessageDto.toMessageUi(): MessagesUi {
    return MessagesUi(
        id = id,
        image = image,
        owner = owner,
        lastMessage = lastMessage,
        lastActive = lastActive,
        unreadMessages = unreadMessages,
        isTyping = isTyping,
        lastMessageType = MessageType.getFromType(lastMessageType)
    )
}
