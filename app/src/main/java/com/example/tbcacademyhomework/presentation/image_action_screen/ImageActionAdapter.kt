package com.example.tbcacademyhomework.presentation.image_action_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.databinding.ItemImageActionBinding
import com.example.tbcacademyhomework.presentation.common.extension.loadImage

class ImageActionAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<ImageActionAdapter.ImageActionViewHolder>() {

    private val items = ImageAction.entries.toList()

    inner class ImageActionViewHolder(private val binding: ItemImageActionBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            val item = items[adapterPosition]
            with(binding) {
                tvImageAction.text = root.context.getString(item.title)
                ivImageAction.loadImage(item.image)
                root.setOnClickListener {
                    onClick(item.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageActionViewHolder {
        val binding =
            ItemImageActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageActionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageActionViewHolder, position: Int) {
        holder.bind()
    }
}