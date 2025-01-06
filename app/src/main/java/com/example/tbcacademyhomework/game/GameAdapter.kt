package com.example.tbcacademyhomework.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemGameCellBinding
import java.util.UUID

class GameAdapter : ListAdapter<GameSquare, GameAdapter.GameViewHolder>(GameDiffUtil()) {

    inner class GameViewHolder(private val binding: ItemGameCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            with(binding) {
                if (item.playerType == PlayerType.X) {
                    val background = ContextCompat.getDrawable(root.context, R.drawable.img_player2)
                    root.setImageDrawable(background)
                } else if (item.playerType == PlayerType.O) {
                    val background = ContextCompat.getDrawable(root.context, R.drawable.img_player1)
                    root.setImageDrawable(background)
                } else {
                    root.setImageDrawable(null)
                }

                root.setOnClickListener {
                    listener(item.id)
                }
            }

        }

    }

    private var listener: (UUID) -> Unit = {}

    fun onItemClick(listener: (UUID) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding =
            ItemGameCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GameViewHolder(binding)

    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind()
    }
}


private class GameDiffUtil : DiffUtil.ItemCallback<GameSquare>() {
    override fun areItemsTheSame(oldItem: GameSquare, newItem: GameSquare): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameSquare, newItem: GameSquare): Boolean {
        return oldItem == newItem
    }

}