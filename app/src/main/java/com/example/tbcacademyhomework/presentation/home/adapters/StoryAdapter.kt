package com.example.tbcacademyhomework.presentation.home.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemStoryBinding
import com.example.tbcacademyhomework.presentation.model.Story
import com.example.tbcacademyhomework.presentation.util.ext.loadImage

class StoryAdapter : ListAdapter<Story, StoryAdapter.StoryViewHolder>(StoryDiffUtil()) {


    inner class StoryViewHolder(private val binding: ItemStoryBinding) : ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                ivCover.loadImage(
                    item.cover,
                    placeholderResId = R.drawable.ic_refresh,
                    errorResId = R.drawable.ic_person
                )
                tvTitle.text = item.title

            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind()
    }
}

private class StoryDiffUtil : DiffUtil.ItemCallback<Story>() {
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }

}