package com.example.tbcacademyhomework.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder(view: View) : ViewHolder(view) {
    abstract fun bind()
}