package com.example.tbcacademyhomework.order.models

import androidx.annotation.DrawableRes
import com.example.tbcacademyhomework.product.ProductColor
import java.io.Serializable
import java.math.BigDecimal
import java.util.UUID

data class Order(
    val id: UUID = UUID.randomUUID(),
    val productColor: ProductColor,
    val productName: String,
    val orderStatus: OrderStatus,
    val quantity: Int,
    val rating: Rating? = null,
    val price: BigDecimal,
    @DrawableRes
    val productImg: Int
):Serializable
