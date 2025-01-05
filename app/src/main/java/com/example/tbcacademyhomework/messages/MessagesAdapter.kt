package com.example.tbcacademyhomework.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tbcacademyhomework.databinding.ItemChatOtherBinding
import com.example.tbcacademyhomework.databinding.ItemChatSelfBinding
import com.example.tbcacademyhomework.util.BaseViewHolder

class MessagesAdapter : ListAdapter<Message, BaseViewHolder>(MessagesDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.messageType == MessageType.SELF) ITEM_SELF else ITEM_OTHER

    }

    private var listUpdateListener: () -> Unit = {}

    fun onListUpdated(listener: () -> Unit) {
        listUpdateListener = listener
    }

    override fun submitList(list: List<Message>?) {
        super.submitList(list) {
            listUpdateListener.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (viewType == ITEM_SELF) {
            val binding =
                ItemChatSelfBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SelfMessagesViewHolder(binding)
        } else {
            val binding =
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return OtherMessagesViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind()
    }

    companion object {
        const val ITEM_SELF = 1
        const val ITEM_OTHER = 0
    }

    inner class SelfMessagesViewHolder(private val binding: ItemChatSelfBinding) :
        BaseViewHolder(binding.root) {
        override fun bind() {
            val item = getItem(adapterPosition)

            with(binding) {
                tvMessage.text = item.message
                tvTime.text = item.timeStamp
            }

        }

    }

    inner class OtherMessagesViewHolder(private val binding: ItemChatOtherBinding) :
        BaseViewHolder(binding.root) {
        override fun bind() {
            val item = getItem(adapterPosition)

            with(binding) {
                tvMessage.text = item.message
                tvTime.text = item.timeStamp
            }
        }

    }


}


class MessagesDiffUtil : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}