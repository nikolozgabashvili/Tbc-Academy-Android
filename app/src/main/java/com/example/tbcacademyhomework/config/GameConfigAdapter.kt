package com.example.tbcacademyhomework.config

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemConfigurationBinding
import java.util.UUID

class GameConfigAdapter :
    ListAdapter<ConfigurationItem, GameConfigAdapter.ConfigViewHolder>(ConfigDiffUtil()) {

    private var listener: (UUID) -> Unit = {}

    fun onClickListener(listener: (UUID) -> Unit) {
        this.listener = listener
    }

    inner class ConfigViewHolder(private val binding: ItemConfigurationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                if (item.selected) {
                    val color = root.context.getColor(R.color.black)
                    root.strokeColor = color
                } else {
                    root.strokeColor = root.context.getColor(R.color.white)
                }
                tvConfValue.text = item.value.toString()

                root.setOnClickListener {
                    listener(item.id)
                }
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigViewHolder {
        val binding =
            ItemConfigurationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ConfigViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ConfigViewHolder, position: Int) {
        holder.bind()
    }
}

private class ConfigDiffUtil : DiffUtil.ItemCallback<ConfigurationItem>() {
    override fun areItemsTheSame(oldItem: ConfigurationItem, newItem: ConfigurationItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ConfigurationItem,
        newItem: ConfigurationItem
    ): Boolean {
        return oldItem == newItem
    }

}