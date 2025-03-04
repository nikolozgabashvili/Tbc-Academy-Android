package com.example.tbcacademyhomework.presentation.settings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.databinding.ItemSettingBinding
import com.example.tbcacademyhomework.presentation.core.util.loadImage
import com.example.tbcacademyhomework.presentation.core.util.visibleIf

class SettingItemsAdapter(
    private val items: List<SettingItemType>,
    private val onItemClicked: (SettingItemType) -> Unit
) : RecyclerView.Adapter<SettingItemsAdapter.SettingItemViewHolder>() {

    inner class SettingItemViewHolder(private val binding: ItemSettingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = items[position]
            with(binding) {
                tvSettingTitle.text = root.context.getString(item.titleRes)
                ivStartView.loadImage(item.iconRes)
                divider.visibleIf(item != items.last(), gone = false)
                root.setOnClickListener {
                    onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingItemViewHolder {
        val binding = ItemSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}