package com.example.tbcacademyhomework.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemOrderBinding
import com.example.tbcacademyhomework.toDateString
import java.util.UUID

class OrderAdapter : ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderDiffUtil()) {

    private var listener: (UUID) -> Unit = {}

    fun onDetailsClick(listener: (UUID) -> Unit) {
        this.listener = listener

    }


    class OrderDiffUtil : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind()
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                val statusColor = root.context.getColor(item.status.color)
                tvOrderId.text = root.context.getString(R.string.order_name, item.orderNumber.toString())
                tvSubtotal.text = root.context.getString(R.string.subtotal_value, item.subtotal.toString())
                tvDate.text = item.orderTime.toDateString()
                tvTrackingNumber.text = item.trackingNumber
                tvQuantity.text = item.quantity.toString()
                tvStatus.text = item.status.name
                tvStatus.setTextColor(statusColor)

                btnDetails.setOnClickListener {
                    listener(item.id)
                }
            }

        }
    }
}