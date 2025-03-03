package com.example.tbcacademyhomework.presentation.core.validation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemValidationBinding
import com.example.tbcacademyhomework.presentation.core.util.loadImage

class ValidationAdapter :
    ListAdapter<ValidationItem, ValidationAdapter.ValidationViewHolder>(ValidationDiffUtil()) {

    inner class ValidationViewHolder(private val binding: ItemValidationBinding) :
        ViewHolder(binding.root) {
        fun bind() {

            val item = getItem(adapterPosition)
            with(binding) {
                if (item.isValid) {
                    ivValidationStatus.loadImage(R.drawable.ic_valid)
                } else {
                    ivValidationStatus.loadImage(R.drawable.ic_not_valid)
                }
                tvValidationMessage.text = item.message
            }

        }

    }

    override fun onBindViewHolder(holder: ValidationViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidationViewHolder {
        val binding =
            ItemValidationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidationViewHolder(binding)
    }
}

private class ValidationDiffUtil : DiffUtil.ItemCallback<ValidationItem>() {
    override fun areItemsTheSame(oldItem: ValidationItem, newItem: ValidationItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ValidationItem, newItem: ValidationItem): Boolean {
        return oldItem == newItem
    }
}