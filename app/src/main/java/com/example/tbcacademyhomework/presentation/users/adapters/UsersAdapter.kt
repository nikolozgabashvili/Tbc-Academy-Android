package com.example.tbcacademyhomework.presentation.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemUserBinding
import com.example.tbcacademyhomework.presentation.users.models.UserUi
import com.example.tbcacademyhomework.presentation.utils.ext.loadImage

class UsersAdapter : ListAdapter<UserUi, UsersAdapter.UserViewHolder>(UserDiffUtil()) {

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            item?.let {
                with(binding) {
                    ivUser.loadImage(item.avatar, placeholder = R.drawable.ic_person)
                    tvUserName.text = item.firstName
                    tvUserLastName.text = item.lastName
                    tvUserEmail.text = item.email
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }
}


private class UserDiffUtil : DiffUtil.ItemCallback<UserUi>() {
    override fun areItemsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
        return oldItem == newItem
    }

}