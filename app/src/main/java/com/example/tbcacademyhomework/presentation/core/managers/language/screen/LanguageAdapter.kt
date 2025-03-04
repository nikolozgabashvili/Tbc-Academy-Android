package com.example.tbcacademyhomework.presentation.core.managers.language.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.databinding.ItemLanguageBinding
import com.example.tbcacademyhomework.domain.core.managers.language.AppLanguage
import com.example.tbcacademyhomework.presentation.core.util.visibleIf

class LanguageAdapter(private val onLanguageSelected: (AppLanguage) -> Unit) :
    ListAdapter<AppLanguageItem, LanguageAdapter.LanguageViewHolder>(LanguageDiffUtil()) {

    inner class LanguageViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                tvLanguageIcon.text = item.language.emojiString
                tvLanguage.text = item.language.displayString
                rbLanguage.isClickable = false
                rbLanguage.isChecked = item.isSelected
                divider.visibleIf(item.hadUnderLine)

                root.setOnClickListener {
                    if (!item.isSelected)
                        onLanguageSelected(item.language)
                }
            }
        }

    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }
}

private class LanguageDiffUtil : DiffUtil.ItemCallback<AppLanguageItem>() {
    override fun areContentsTheSame(oldItem: AppLanguageItem, newItem: AppLanguageItem): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: AppLanguageItem, newItem: AppLanguageItem): Boolean {
        return oldItem.language == newItem.language
    }

}