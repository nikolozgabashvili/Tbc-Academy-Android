package com.example.tbcacademyhomework.order

import java.util.UUID

data class StatusItem(
    val id: UUID = UUID.randomUUID(),
    val status: OrderStatus,
    val isSelected: Boolean = false
)
