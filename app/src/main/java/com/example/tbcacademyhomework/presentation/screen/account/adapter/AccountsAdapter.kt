package com.example.tbcacademyhomework.presentation.screen.account.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemAccountBinding
import com.example.tbcacademyhomework.presentation.common.extension.loadImage
import com.example.tbcacademyhomework.presentation.screen.account.model.AccountUi

class AccountsAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<AccountUi, AccountsAdapter.AccountsViewHolder>(AccountDiffUtil()) {

    inner class AccountsViewHolder(private val binding: ItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                ivAccount.loadImage(item.cardLogo)
                tvAccountName.text = item.accountName
                tvAccountBalance.text = root.context.getString(
                    R.string.formatted_balance,
                    item.balance.toString(),
                    item.currencyType.displayName
                )
                tvAccountCardNum.text = root.context.getString(
                    R.string.formatted_card_number,
                    item.accountNumber.takeLast(4)
                )

                root.setOnClickListener {
                    onClick(item.id)
                }

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        holder.bind()
    }


}

private class AccountDiffUtil : DiffUtil.ItemCallback<AccountUi>() {
    override fun areItemsTheSame(oldItem: AccountUi, newItem: AccountUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AccountUi, newItem: AccountUi): Boolean {
        return oldItem == newItem
    }

}
