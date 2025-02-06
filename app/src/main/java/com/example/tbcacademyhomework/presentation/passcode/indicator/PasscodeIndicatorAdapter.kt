package com.example.tbcacademyhomework.presentation.passcode.indicator

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemPasscodeIndicatorBinding

class PasscodeIndicatorAdapter :
    ListAdapter<PasscodeIndicator, PasscodeIndicatorAdapter.PasscodeIndicatorViewHolder>(
        PasscodeIndicatorDiffUtil()
    ) {

    inner class PasscodeIndicatorViewHolder(private val binding: ItemPasscodeIndicatorBinding) :
        ViewHolder(binding.root) {

        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                val color = if (item.isSelected) R.color.green else R.color.gray
                root.backgroundTintList = ColorStateList.valueOf(root.context.getColor(color))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasscodeIndicatorViewHolder {
        val binding =
            ItemPasscodeIndicatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PasscodeIndicatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasscodeIndicatorViewHolder, position: Int) {
        holder.bind()
    }


}

private class PasscodeIndicatorDiffUtil : DiffUtil.ItemCallback<PasscodeIndicator>() {
    override fun areItemsTheSame(oldItem: PasscodeIndicator, newItem: PasscodeIndicator): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PasscodeIndicator,
        newItem: PasscodeIndicator
    ): Boolean {
        return oldItem == newItem
    }
}