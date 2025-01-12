package com.example.tbcacademyhomework.order.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemOrderBinding
import com.example.tbcacademyhomework.order.models.Order
import com.example.tbcacademyhomework.order.models.OrderStatus
import java.util.UUID

class OrderListAdapter(
    val onclick: (UUID) -> Unit,
) : ListAdapter<Order, OrderListAdapter.OrderViewHolder>(OrdersDiffUtil()) {

    override fun submitList(list: List<Order>?) {
        super.submitList(list)
        println(list)
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) : ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                tvProductName.text = item.productName
                val itemColor = item.productColor.color
                val status = when (item.orderStatus) {
                    OrderStatus.ACTIVE -> R.string.in_delivery
                    OrderStatus.COMPLETED -> R.string.completed
                }

                ivProduct.setImageResource(item.productImg)
                with(root.context) {
                    btnAction.text =
                        if (item.orderStatus == OrderStatus.ACTIVE) getString(R.string.track_order)
                        else if (item.rating == null) getString(R.string.leave_review)
                        else getString(R.string.buy_again)

                    tvColorName.text = getString(item.productColor.displayName)

                    tvStatus.text = getString(status)

                    vProductColor.backgroundTintList =
                        ColorStateList.valueOf(getColor(itemColor))

                    tvPrice.text = getString(
                        R.string.price,
                        item.price * item.quantity.toBigDecimal()
                    )
                    tvQuantity.text = getString(R.string.qty, item.quantity.toString())

                }

                root.setOnClickListener {
                    if (item.orderStatus == OrderStatus.COMPLETED && item.rating == null) {
                        onclick(item.id)
                    }
                }

                btnAction.setOnClickListener {
                    if (item.orderStatus == OrderStatus.COMPLETED && item.rating == null) {
                        onclick(item.id)
                    }
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind()
    }
}

private class OrdersDiffUtil : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }

}