package com.example.tbcacademyhomework.order.models

import java.io.Serializable
import java.util.UUID

data class Rating(
    val orderId: UUID,
    val starRating: Float,
    val comment: String
) : Serializable
