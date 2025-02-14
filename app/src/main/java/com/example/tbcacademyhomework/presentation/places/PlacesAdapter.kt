package com.example.tbcacademyhomework.presentation.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tbcacademyhomework.databinding.ItemPlaceBinding
import com.example.tbcacademyhomework.domain.models.Place

class PlacesAdapter:
    ListAdapter<Place, PlacesAdapter.PlacesViewHolder>(PlacesDiffUtil()) {

    inner class PlacesViewHolder(private val binding: ItemPlaceBinding) : ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            with(binding) {
                Glide.with(root).load(item.cover).into(ivPlace)
                tvLocation.text = item.location
                tvReactionCount.text = item.reactionCount.toString()
                tvTitle.text = item.title
                tvPrice.text = item.price
                ratingBar.rating = item.rating.toFloat()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bind(position)
    }
}

private class PlacesDiffUtil : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem == newItem
    }

}