package com.example.tbcacademyhomework.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemPostBinding
import com.example.tbcacademyhomework.presentation.model.Post
import com.example.tbcacademyhomework.presentation.util.ext.loadImage

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffUtil()) {

    inner class PostViewHolder(private val binding: ItemPostBinding) : ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            handleImages(item, binding)
            with(binding) {
                tvPostTimestamp.text = item.owner.postDate
                item.owner.profile?.let { image ->
                    ivOwner.loadImage(
                        image,
                        placeholderResId = R.drawable.ic_refresh,
                        errorResId = R.drawable.ic_person
                    )
                } ?: run { ivOwner.loadImage(R.drawable.ic_person) }
                tvOwnerName.text = item.owner.fullName
                tvPostTitle.text = item.title
                itemComment.ivReact.loadImage(R.drawable.ic_comment)
                itemComment.tvReact.text =
                    root.context.getString(R.string.comments, item.comments.toString())
                itemLike.ivReact.loadImage(R.drawable.ic_like)
                itemLike.tvReact.text =
                    root.context.getString(R.string.likes, item.likes.toString())
                itemShare.ivReact.loadImage(R.drawable.ic_share)
                itemShare.tvReact.text = root.context.getString(R.string.share)

            }

        }

        private fun handleImages(item: Post?, binding: ItemPostBinding) {
            with(binding) {
                if (item?.images.isNullOrEmpty()) {
                    ivPostImage1.isVisible = false
                    ivPostImage2.isVisible = false
                    ivPostImage3.isVisible = false
                } else
                    when (item?.images?.size) {
                        1 -> {
                            ivPostImage1.isVisible = true
                            ivPostImage2.isVisible = false
                            ivPostImage3.isVisible = false

                            ivPostImage1.loadImage(image = item.images[0])
                        }

                        2 -> {
                            ivPostImage1.isVisible = true
                            ivPostImage2.isVisible = true
                            ivPostImage3.isVisible = false

                            ivPostImage1.loadImage(image = item.images[0])
                            ivPostImage2.loadImage(image = item.images[1])
                        }

                        3 -> {
                            ivPostImage1.isVisible = true
                            ivPostImage2.isVisible = true
                            ivPostImage3.isVisible = true

                            ivPostImage1.loadImage(image = item.images[0])
                            ivPostImage2.loadImage(image = item.images[1])
                            ivPostImage3.loadImage(image = item.images[2])
                        }
                    }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind()
    }
}


private class PostDiffUtil : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}