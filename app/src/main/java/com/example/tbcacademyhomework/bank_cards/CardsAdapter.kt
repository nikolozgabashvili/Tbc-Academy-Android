package com.example.tbcacademyhomework.bank_cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.databinding.ItemBankCardBinding

class CardsAdapter(
    private val onLongClick: (id: String) -> Unit
) : ListAdapter<BankCard, CardsAdapter.CardsViewHolder>(CardDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val binding =
            ItemBankCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CardsViewHolder(binding)
    }

    private var dataCommitListener: () -> Unit = {}
    fun onDataCommitListener(listener: () -> Unit) {
        dataCommitListener = listener
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind()
    }

    override fun submitList(list: List<BankCard>?) {
        super.submitList(list) {
            dataCommitListener()
        }
    }

    inner class CardsViewHolder(private val binding: ItemBankCardBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding.root) {
                cardHolderName = item.cardHolder
                cardType = item.cardType
                expirationDate = "${item.expirationMonth}/${item.expirationYear}"
                cardNumber = item.cardNumber

                setOnLongClickListener {
                    onLongClick(item.id)
                    true
                }

            }


        }

    }


}

private class CardDiffUtil : DiffUtil.ItemCallback<BankCard>() {
    override fun areItemsTheSame(oldItem: BankCard, newItem: BankCard): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BankCard, newItem: BankCard): Boolean {
        return oldItem == newItem
    }

}

