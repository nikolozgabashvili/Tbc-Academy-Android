package com.example.tbcacademyhomework.order

import java.util.UUID

class StatusDatabase {
    private val list = OrderStatus.entries.map {
        StatusItem(
            status = it,
        )
    }.toMutableList()


    fun getStatuses(status: OrderStatus = OrderStatus.PENDING): List<StatusItem> {
        return list.map { it.copy(isSelected = it.status == status) }
    }

    fun getStatusById(id: UUID) = list.find { it.id == id }?.status

}