package com.example.tbcacademyhomework


import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbcacademyhomework.base.BaseViewHolder
import com.example.tbcacademyhomework.databinding.ItemChooserBinding
import com.example.tbcacademyhomework.databinding.ItemInputBinding

class InputAdapter(
    private val list: List<ResponseDataUi>,
    private val inputChanged: (InputItemCallback) -> Unit,
) :
    RecyclerView.Adapter<BaseViewHolder>() {


    inner class InputViewHolder(private val binding: ItemInputBinding) :
        BaseViewHolder(binding.root) {
        override fun bind() {
            val item = list[adapterPosition]
            with(binding) {
                divider.isVisible = adapterPosition < list.lastIndex
                etInput.hint = item.hint
                root.isEnabled = item.isActive == true
                etInput.inputType = when (item.keyboardType) {
                    KeyboardType.TEXT -> InputType.TYPE_CLASS_TEXT
                    KeyboardType.NUMBER -> InputType.TYPE_CLASS_NUMBER
                    null -> InputType.TYPE_CLASS_TEXT

                }

                etInput.doAfterTextChanged {
                    inputChanged(InputItemCallback(id = item.id, value = it.toString()))
                }
                Glide.with(root).load(item.icon).into(ivInputIcon)
            }

        }

    }

    inner class ChooserViewHolder(private val binding: ItemChooserBinding) :
        BaseViewHolder(binding.root) {

        override fun bind() {
            val item = list[adapterPosition]
            with(binding) {
                tvChooser.hint = item.hint
                divider.isVisible = adapterPosition < list.lastIndex
                root.isEnabled = item.isActive == true

                root.setOnClickListener {
                    if (item.hint?.lowercase() == "birthday") {
                        showDatePicker(root.context, onDateSelected = {
                            tvChooser.text = it
                            inputChanged(
                                InputItemCallback(id = item.id, it)
                            )
                        })
                    } else {
                        showDropdownMenu(
                            root,
                            onItemSelected = {
                                tvChooser.text = it
                                inputChanged(
                                    InputItemCallback(id = item.id, it)
                                )
                            },
                            dropDownItems = Gender.entries.map { it.name }
                        )
                    }
                }
            }
        }

    }


    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return when (item.fieldType) {
            FieldType.INPUT -> INPUT_TYPE
            FieldType.CHOOSER -> CHOOSER_TYPE
            null -> INPUT_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            INPUT_TYPE -> {
                val binding =
                    ItemInputBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                InputViewHolder(binding)
            }

            else -> {
                val binding =
                    ItemChooserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ChooserViewHolder(binding)
            }
        }

    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind()
    }

    companion object {
        private const val CHOOSER_TYPE = 1
        private const val INPUT_TYPE = 2
    }
}


