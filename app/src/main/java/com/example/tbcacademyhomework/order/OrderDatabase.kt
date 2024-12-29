package com.example.tbcacademyhomework.order

import java.util.Calendar
import java.util.Locale
import java.util.UUID

class OrderDatabase {
    private val calendar = Calendar.getInstance()
    private val orders = mutableListOf(
        Order(
            orderNumber = 1023,
            trackingNumber = "IK23452354",
            quantity = 2,
            subtotal = 25,
            orderTime = calendar.timeInMillis,
        ),
        Order(
            orderNumber = 1023,
            trackingNumber = "IK23452354",
            quantity = 2,
            subtotal = 12,
            orderTime = calendar.apply { set(2024, 11, 20) }.timeInMillis,
        ),
        Order(
            orderNumber = 1023,
            trackingNumber = "IK23452354",
            quantity = 2,
            subtotal = 43,
            orderTime = calendar.apply { set(2024, 11, 19) }.timeInMillis,
        ),
        Order(
            orderNumber = 1023,
            trackingNumber = "IK23452354",
            quantity = 2,
            subtotal = 12,
            orderTime = calendar.apply { set(2024, 11, 12) }.timeInMillis,
        ),
        Order(
            orderNumber = 1023,
            trackingNumber = "IK23452354",
            quantity = 2,
            subtotal = 26,
            orderTime = calendar.apply { set(2024, 10, 30) }.timeInMillis,
        ),
        Order(
            orderNumber = 1023,
            trackingNumber = "IK23452354",
            quantity = 2,
            subtotal = 45,
            orderTime = calendar.apply { set(2024, 11, 20) }.timeInMillis,
        ),
    )

    fun updateOrderStatus(orderId: UUID, status: OrderStatus) {
        orders.find { it.id == orderId }?.let { order ->
            val orderIndex = orders.indexOf(order)
            orders[orderIndex] = order.copy(status = status)
        }
    }


    fun getOrdersByStatus(status: OrderStatus): List<Order> {
        return orders.filter { it.status == status }
    }

    fun getById(id: UUID): Order? {
        return orders.find { it.id == id }
    }
}