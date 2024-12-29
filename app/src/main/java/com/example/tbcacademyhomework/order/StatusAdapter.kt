package com.example.tbcacademyhomework.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.capitalize
import com.example.tbcacademyhomework.databinding.ItemStatusBinding
import java.util.UUID

class StatusAdapter : ListAdapter<StatusItem, StatusAdapter.FilterViewHolder>(FilterDiffUtil()) {

    private var listener: (UUID) -> Unit = {}

    fun onClick(listener: (UUID) -> Unit) {
        this.listener = listener

    }


    class FilterDiffUtil : DiffUtil.ItemCallback<StatusItem>() {
        override fun areItemsTheSame(oldItem: StatusItem, newItem: StatusItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StatusItem, newItem: StatusItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = ItemStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind()
    }

    inner class FilterViewHolder(private val binding: ItemStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                tvStatus.text = item.status.name.capitalize()
                if (item.isSelected) {
                    root.background =
                        AppCompatResources.getDrawable(root.context, R.drawable.bg_btn_gray)
                    tvStatus.setTextColor(root.context.getColor(R.color.white))
                } else {
                    root.background = null
                    tvStatus.setTextColor(root.context.getColor(R.color.black))
                }

                root.setOnClickListener {
                    listener(item.id)
                }

            }

        }
    }
}