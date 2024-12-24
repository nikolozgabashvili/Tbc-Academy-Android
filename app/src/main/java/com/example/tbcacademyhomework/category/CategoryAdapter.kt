package com.example.tbcacademyhomework.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemCategoryBinding

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var selectedCategory = CategoryType.ALL
    private var listener: ((CategoryType) -> Unit)? = null

    fun onClickListener(listener: (CategoryType) -> Unit) {
        this.listener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount() = categories.size


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            val category = categories[position]
            with(binding) {
                val isSelected = category.type == selectedCategory
                tvCategory.text = category.displayName
                root.setBackgroundResource(
                    if (isSelected) R.drawable.bg_category_selected
                    else R.drawable.bg_category
                )
                tvCategory.setTextColor(
                    root.context.getColor(
                        if (isSelected) R.color.text_white else R.color.text_gray
                    )
                )
                root.setOnClickListener {
                    if (selectedCategory != category.type) {
                        notifyItemChanged(categories.indexOfFirst { it.type == category.type })
                        notifyItemChanged(categories.indexOfFirst { it.type == selectedCategory })
                        selectedCategory = category.type
                        listener?.invoke(selectedCategory)
                    }

                }
            }


        }
    }
}