package com.example.tbcacademyhomework.order

import androidx.annotation.ColorRes
import com.example.tbcacademyhomework.R

enum class OrderStatus(@ColorRes val color:Int) {
    PENDING(R.color.pending),
    DELIVERED(R.color.delivered),
    CANCELLED(R.color.cancelled)
}