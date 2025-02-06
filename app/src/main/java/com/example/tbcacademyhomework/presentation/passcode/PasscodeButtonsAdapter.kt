package com.example.tbcacademyhomework.presentation.passcode


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ItemPasscodeButtonBinding
import com.example.tbcacademyhomework.presentation.passcode.passcode_button.PasscodeButton
import com.example.tbcacademyhomework.presentation.passcode.passcode_button.PasscodeButtonType

class PasscodeButtonsAdapter(
    private val buttons: List<PasscodeButton>,
    private val onclickListener:(PasscodeButtonType) -> Unit
) :
    RecyclerView.Adapter<PasscodeButtonsAdapter.PasscodeViewHolder>() {


    inner class PasscodeViewHolder(private val binding: ItemPasscodeButtonBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            val item = buttons[adapterPosition]
            with(binding) {
                root.setOnClickListener {
                    onclickListener(item.type)
                }
                when (val type = item.type) {

                    is PasscodeButtonType.Number -> {
                        tvValue.isVisible = true
                        ivSymbol.isVisible = false
                        root.background = AppCompatResources.getDrawable(
                            root.context,
                            R.drawable.bg_passcode_button
                        )
                        tvValue.text = type.value

                    }

                    PasscodeButtonType.BackSpace -> {
                        tvValue.isVisible = false
                        ivSymbol.isVisible = true
                        root.background = AppCompatResources.getDrawable(
                            root.context,
                            R.drawable.bg_passcode_button_wild
                        )
                        ivSymbol.setBackgroundResource(R.drawable.ic_backspace)

                    }

                    PasscodeButtonType.FingerPrint -> {
                        tvValue.isVisible = false
                        ivSymbol.isVisible = true
                        root.background = AppCompatResources.getDrawable(
                            root.context,
                            R.drawable.bg_passcode_button_wild
                        )
                        ivSymbol.setBackgroundResource(R.drawable.ic_fingerprint)

                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasscodeViewHolder {
        val binding =
            ItemPasscodeButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PasscodeViewHolder(binding)
    }

    override fun getItemCount() = buttons.size

    override fun onBindViewHolder(holder: PasscodeViewHolder, position: Int) {
        holder.bind()
    }


}