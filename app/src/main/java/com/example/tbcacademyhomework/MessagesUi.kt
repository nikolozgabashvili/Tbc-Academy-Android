package com.example.tbcacademyhomework

import androidx.lifecycle.LifecycleOwner
import com.squareup.moshi.Json

data class MessagesUi(
    val id: Int,
    val image: String?,
    val owner: String,
    val lastMessage: String,
    val lastActive: String,
    val unreadMessages: Int,
    val isTyping: Boolean,
    val lastMessageType: MessageType
)
