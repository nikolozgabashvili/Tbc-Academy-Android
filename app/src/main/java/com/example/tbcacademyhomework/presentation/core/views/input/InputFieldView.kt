package com.example.tbcacademyhomework.presentation.core.views.input

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.InputFieldViewBinding

class InputFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    private var isPasswordVisible = false
        set(value) {
            field = value
            setupPasswordInput()
        }


    private val binding = InputFieldViewBinding.inflate(LayoutInflater.from(context), this)
    private var onTextChangedListener: ((String) -> Unit)? = null

    var errorText: String = ""
        set(value) {
            field = value
            binding.tvError.text = errorText
        }

    var isError: Boolean = false
        set(value) {
            field = value
            updateErrorState()
        }


    private var inputType: InputType = InputType.STANDARD
        set(value) {
            field = value
            setType(value)
        }

    var text: String = ""
        get() = binding.etInput.text.toString()
        set(value) {
            field = value
            binding.etInput.setText(value)
        }

    var hint: String? = null
        set(value) {
            field = value
            binding.etInput.hint = value
        }

    var startIcon: Int? = null
        set(value) {
            if (value != RESOURCE_DEFAULT_VALUE) {
                field = value
                setInputStartIcon(startIcon)
            }
        }

    var endIcon: Int? = null
        set(value) {
            if (value != RESOURCE_DEFAULT_VALUE) {
                field = value
                setInputEndIcon(endIcon)
            }
        }

    var textColor: Int = R.color.black
        set(value) {
            field = value
            binding.etInput.setTextColor(value)
        }

    private var hintColor: Int = R.color.gray
        set(value) {
            field = value
            binding.etInput.setHintTextColor(value)
        }


    init {

        context.withStyledAttributes(
            attrs,
            R.styleable.InputFieldView
        ) {

            inputType = InputType.getEnumWithValue(
                getInt(R.styleable.InputFieldView_inputType, 1)
            )

            hint = getString(R.styleable.InputFieldView_hint)

            startIcon = getResourceId(R.styleable.InputFieldView_startIcon, RESOURCE_DEFAULT_VALUE)

            endIcon = getResourceId(
                R.styleable.InputFieldView_endIcon,
                RESOURCE_DEFAULT_VALUE
            )
            textColor = getColor(
                R.styleable.InputFieldView_textColor,
                ContextCompat.getColor(context, R.color.black)
            )
            hintColor = getColor(
                R.styleable.InputFieldView_hintColor,
                ContextCompat.getColor(context, R.color.gray)
            )

        }



        initListeners()
    }


    private fun initListeners() {
        binding.ivPasswordToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
        }

        binding.root.setOnClickListener {
            showKeyboard(binding.etInput)


        }


        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isError = false
                onTextChangedListener?.invoke(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

    fun setOnTextChangedListener(listener: (String) -> Unit) {
        onTextChangedListener = listener
    }

    private fun showKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = getSystemService(context, InputMethodManager::class.java)
            imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }


    private fun setInputStartIcon(@DrawableRes iconRes: Int?) {
        iconRes?.let {
            binding.ivStartIcon.setImageResource(iconRes)
            binding.ivStartIcon.isVisible = true
        } ?: run {
            binding.ivStartIcon.isVisible = false
        }
    }


    private fun setInputEndIcon(@DrawableRes iconRes: Int?) {
        if (inputType != InputType.PASSWORD) {
            iconRes?.let {
                binding.ivEndIcon.setImageResource(iconRes)
                binding.ivEndIcon.isVisible = true
                binding.ivPasswordToggle.isVisible = false
            } ?: run {
                binding.ivEndIcon.isVisible = false
            }
        }
    }

    private fun setupPasswordInput() {
        if (inputType == InputType.PASSWORD) {
            if (isPasswordVisible) {
                binding.etInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.ivPasswordToggle.setImageResource(R.drawable.ic_visibility)
            } else {

                binding.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.ivPasswordToggle.setImageResource(R.drawable.ic_visibility_off)
            }

            binding.etInput.text?.let { binding.etInput.setSelection(it.length) }
        }

    }

    private fun updateErrorState() {
        val showError = isError && errorText.isNotEmpty()
        binding.errorLayout.isVisible = showError
        val strokeColor = ContextCompat.getColor(
            context,
            if (showError) R.color.text_error else R.color.input_stroke
        )

        binding.card.strokeColor = strokeColor

    }

    private fun setType(inputType: InputType) {

        if (inputType == InputType.PASSWORD) {

            binding.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.ivPasswordToggle.isVisible = true
            binding.ivEndIcon.isVisible = false
            isPasswordVisible = false
        } else {

            binding.etInput.transformationMethod = null
            binding.ivPasswordToggle.isVisible = false
        }
    }


    companion object {
        const val RESOURCE_DEFAULT_VALUE = -1
    }
}