package com.example.tbcacademyhomework.presentation.meal.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.tbcacademyhomework.presentation.meal.home.model.MealByCategory


private class MealDiffUtil : DiffUtil.ItemCallback<MealByCategory>() {
    override fun areItemsTheSame(oldItem: MealByCategory, newItem: MealByCategory): Boolean {
        return oldItem.categoryId == newItem.categoryId
    }

    override fun areContentsTheSame(oldItem: MealByCategory, newItem: MealByCategory): Boolean {
        return oldItem == newItem
    }
}