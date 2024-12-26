package com.example.tbcacademyhomework.adapters.icon


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemLocationIconBinding
import com.example.tbcacademyhomework.models.LocationIcon

class LocationIconAdapter :
    ListAdapter<LocationIcon, LocationIconAdapter.LocationIconViewHolder>(DiffUtil()) {

    private var listener: (Int) -> Unit = {}

    fun onCLick(listener: (Int) -> Unit) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationIconViewHolder {
        val binding =
            ItemLocationIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationIconViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationIconViewHolder, position: Int) {
        holder.bind()

    }

    inner class LocationIconViewHolder(private val binding: ItemLocationIconBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val icon = getItem(adapterPosition)
            with(binding) {
                ivIcon.setImageResource(icon.iconRes)
                val strokeColorDrawable =
                    if (icon.isSelected) R.color.card_stroke_color_darker else R.color.card_stroke_color
                root.strokeColor = root.context.getColor(strokeColorDrawable)

                root.setOnClickListener {
                    listener(icon.id)
                }
            }
        }
    }

    private class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<LocationIcon>() {
        override fun areItemsTheSame(oldItem: LocationIcon, newItem: LocationIcon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationIcon, newItem: LocationIcon): Boolean {
            return oldItem == newItem
        }
    }
}