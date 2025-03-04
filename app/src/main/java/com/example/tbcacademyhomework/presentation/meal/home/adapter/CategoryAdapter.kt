package com.example.tbcacademyhomework.presentation.meal.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.databinding.ItemCategoryBinding
import com.example.tbcacademyhomework.presentation.meal.home.model.CategoryUi

class CategoryAdapter(
    private val onCategoryClick: (id: CategoryUi) -> Unit
) :
    ListAdapter<CategoryUi, CategoryAdapter.CategoryViewHolder>(CategoryDiffUtil()) {


    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        ViewHolder(binding.root) {

        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                tvCategory.text = item.name
                frameCategory.isSelected = item.isSelected
                frameCategory.setOnClickListener {
                    if (!item.isSelected) {
                        onCategoryClick(item)
                    }
                }
            }

        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

}

private class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryUi>() {
    override fun areContentsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return oldItem.id == newItem.id
    }

}