package com.example.tbcacademyhomework.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemUserBinding

class UsersAdapter : ListAdapter<User, UsersAdapter.UserViewHolder>(UserDiffUtil()) {

    inner class UserViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {

        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                Glide.with(root)
                    .load(item.icon)
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(ivUser)
                tvUserName.text = item.name
                tvUserLastName.text = item.lastName
                tvUserEmail.text = item.email
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }
}


private class UserDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}