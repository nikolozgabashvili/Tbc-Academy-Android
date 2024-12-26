package com.example.tbcacademyhomework.adapters.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.databinding.ItemAddressBinding
import com.example.tbcacademyhomework.models.Address


class AddressAdapter : ListAdapter<Address, AddressAdapter.AddressViewHolder>(DiffUtil()) {

    private var listener: (AddressAdapterCallback) -> Unit = {}

    fun onCallback(listener: (AddressAdapterCallback) -> Unit) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind()
    }


    inner class AddressViewHolder(private val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val address = getItem(adapterPosition)
            with(binding) {
                val selected = address.isSelected
                tvAddress.text = address.address
                tvAddressName.text = address.title
                ivLocation.setImageResource(address.image)
                rBtnSelect.isChecked = selected
                tvEdit.isEnabled = selected

                tvEdit.setOnClickListener {

                    listener(AddressAdapterCallback.OnEditClick(address.id))
                }
                root.setOnLongClickListener {
                    listener(AddressAdapterCallback.OnLongClick(address.id))
                    true
                }

                root.setOnClickListener {
                    if (!selected) listener(AddressAdapterCallback.OnClick(address.id))
                }
            }


        }
    }


    private class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Address>() {
        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem == newItem
        }

    }
}