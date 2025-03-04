package com.example.tbcacademyhomework.presentation.core.managers.theme


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.databinding.ItemThemeBinding
import com.example.tbcacademyhomework.domain.core.managers.theme.Theme
import com.example.tbcacademyhomework.presentation.core.util.loadImage
import com.example.tbcacademyhomework.presentation.core.util.visibleIf

class ThemeAdapter(
    private val onThemeSelected: (Theme) -> Unit,
) : ListAdapter<ThemeItem, ThemeAdapter.ThemeViewHolder>(ThemeDiffUtil()) {

    inner class ThemeViewHolder(private val binding: ItemThemeBinding) : ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                tvTheme.text = item.theme.getDisplayableName(context = root.context)
                rbTheme.isChecked = item.isSelected
                divider.visibleIf(item.hasUnderLine)
                ivEndIcon.loadImage(item.theme.getDisplayableDrawable())
                rbTheme.isClickable = false
                root.setOnClickListener {
                    if (!item.isSelected)
                        onThemeSelected(item.theme)
                }


            }


        }
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val binding = ItemThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThemeViewHolder(binding)
    }
}


private class ThemeDiffUtil : DiffUtil.ItemCallback<ThemeItem>() {
    override fun areContentsTheSame(oldItem: ThemeItem, newItem: ThemeItem): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ThemeItem, newItem: ThemeItem): Boolean {
        return oldItem.theme == newItem.theme
    }

}