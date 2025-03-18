package com.example.tbcacademyhomework.presentation.screen.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.databinding.ItemDotBinding
import com.example.tbcacademyhomework.presentation.screen.category.model.DepthIndicator

class CategoryDepthAdapter :
    ListAdapter<DepthIndicator, CategoryDepthAdapter.CategoryDepthViewHolder>(CategoryDepthDiffUtil()) {

    inner class CategoryDepthViewHolder(binding: ItemDotBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDepthViewHolder {
        val binding = ItemDotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryDepthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryDepthViewHolder, position: Int) {
    }
}

private class CategoryDepthDiffUtil : DiffUtil.ItemCallback<DepthIndicator>() {
    override fun areItemsTheSame(oldItem: DepthIndicator, newItem: DepthIndicator): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DepthIndicator, newItem: DepthIndicator): Boolean {
        return oldItem == newItem
    }

}