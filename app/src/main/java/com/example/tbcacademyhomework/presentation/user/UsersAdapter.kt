package com.example.tbcacademyhomework.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemUserBinding
import com.example.tbcacademyhomework.presentation.model.UserUi
import com.example.tbcacademyhomework.util.toStatusString

class UsersAdapter : ListAdapter<UserUi, UsersAdapter.UserViewHolder>(UsersDiffUtil()) {

    inner class UserViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                tvUserAbout.text = item.about
                Glide.with(root)
                    .load(item.avatar)
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_error)
                    .into(ivUser)
                tvUserFullName.text = root.context.getString(
                    R.string.user_full_name_display,
                    item.firstName,
                    item.lastName
                )

                tvUserStatus.text = item.activationStatus.toStatusString(root.context)
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


private class UsersDiffUtil : DiffUtil.ItemCallback<UserUi>() {
    override fun areItemsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserUi, newItem: UserUi): Boolean {
        return oldItem == newItem
    }

}