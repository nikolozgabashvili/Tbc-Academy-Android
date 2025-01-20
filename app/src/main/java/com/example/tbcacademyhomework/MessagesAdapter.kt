package com.example.tbcacademyhomework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbcacademyhomework.databinding.ItemMessageBinding


class MessagesAdapter :
    ListAdapter<MessagesUi, MessagesAdapter.MessagesViewHolder>(MessagesDiffUtil()) {

    inner class MessagesViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                val
                Glide.with(root)
                    .load(item.image)
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(binding.ivOwner)
                tvOwner.text = item.owner
                tvLastTimeStamp.text = item.lastActive

                ivOwner.isVisible = item.lastMessageType != MessageType.TEXT
                when (item.lastMessageType) {
                    MessageType.TEXT -> Unit
                    MessageType.VOICE -> ivOwner.setImageResource(R.drawable.bg_message_type_voice)
                    MessageType.File -> ivOwner.setImageResource(R.drawable.bg_message_type_file)
                }
                when (item.lastMessageType) {
                    MessageType.TEXT -> tvLastMessage.text = item.lastMessage
                    MessageType.VOICE -> tvLastMessage.text =
                        root.context.getString(R.string.voice_message)

                    MessageType.File -> tvLastMessage.text =
                        root.context.getString(R.string.file_message)
                }

                tvMessageCount.isVisible = item.unreadMessages > 0 || item.isTyping
                if (item.isTyping) {
                    tvMessageCount.setBackgroundResource(R.drawable.ic_typing)
                    tvMessageCount.text = ""
                } else if (item.unreadMessages > 0) {
                    tvMessageCount.setBackgroundResource(R.drawable.bg_message_count)
                    tvMessageCount.text = item.unreadMessages.toString()
                }


            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.bind()
    }
}


private class MessagesDiffUtil() : DiffUtil.ItemCallback<MessagesUi>() {
    override fun areItemsTheSame(oldItem: MessagesUi, newItem: MessagesUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessagesUi, newItem: MessagesUi): Boolean {
        return oldItem == newItem
    }

}