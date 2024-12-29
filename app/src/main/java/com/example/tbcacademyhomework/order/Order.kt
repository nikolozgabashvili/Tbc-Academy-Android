package com.example.tbcacademyhomework.order

import java.util.UUID

data class Order(
    val id: UUID = UUID.randomUUID(),
    val orderNumber: Int,
    val trackingNumber: String,
    val quantity: Int,
    val subtotal: Int,
    val orderTime: Long,
    val status: OrderStatus = OrderStatus.PENDING,
)
