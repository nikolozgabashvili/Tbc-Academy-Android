package com.example.tbcacademyhomework.presentation.meal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.databinding.ItemMealBinding
import com.example.tbcacademyhomework.presentation.core.util.GenericImage
import com.example.tbcacademyhomework.presentation.core.util.loadImage
import com.example.tbcacademyhomework.presentation.meal.home.model.MealUi

class MealAdapter : ListAdapter<MealUi, MealAdapter.MealViewHolder>(MealDiffUtil()) {

    inner class MealViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            binding.tvMealName.text = item.mealName
            val image = GenericImage.NetworkImage(
                url = item.mealImage,
            )
            binding.ivMeal.loadImage(image)

        }

    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }
}

private class MealDiffUtil : DiffUtil.ItemCallback<MealUi>() {
    override fun areContentsTheSame(oldItem: MealUi, newItem: MealUi): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MealUi, newItem: MealUi): Boolean {
        return oldItem.mealId == newItem.mealId
    }

}