package com.example.tbcacademyhomework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.databinding.ItemInputGroupBinding
import com.example.tbcacademyhomework.models.InputItemCallback
import com.example.tbcacademyhomework.models.ResponseDataUi

class InputGroupAdapter(
    private val list: List<List<ResponseDataUi>>,
    private val inputChanged: (InputItemCallback) -> Unit
) :
    RecyclerView.Adapter<InputGroupAdapter.InputGroupViewHolder>() {


    inner class InputGroupViewHolder(private val binding: ItemInputGroupBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            val inputList = list[adapterPosition]
            val adapter = InputAdapter(inputList,inputChanged)
            with(binding) {
                rvInputs.layoutManager = LinearLayoutManager(root.context)
                rvInputs.adapter = adapter

            }

        }

    }


    override fun onBindViewHolder(holder: InputGroupViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputGroupViewHolder {
        val binding =
            ItemInputGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InputGroupViewHolder(binding)
    }

    override fun getItemCount() = list.size


}
