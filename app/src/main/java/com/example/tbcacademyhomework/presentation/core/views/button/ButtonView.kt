package com.example.tbcacademyhomework.presentation.core.views.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import androidx.core.view.updatePadding
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ViewButtonBinding
import com.example.tbcacademyhomework.presentation.core.util.GenericImage
import com.example.tbcacademyhomework.presentation.core.util.loadImage
import com.example.tbcacademyhomework.presentation.core.util.setBackgroundDrawable
import com.example.tbcacademyhomework.presentation.core.util.setTextColor
import com.example.tbcacademyhomework.presentation.core.util.setTintColor
import com.example.tbcacademyhomework.presentation.core.util.visibleIf

class ButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = ViewButtonBinding.inflate(LayoutInflater.from(context), this)

    var buttonSize: ButtonSize = ButtonSize.Large
        set(value) {
            field = value
            updateSize()
        }

    var buttonType: ButtonType = ButtonType.Primary
        set(value) {
            field = value
            updateColors()
        }

    var btnEnabled: Boolean = true
        set(value) {
            isEnabled = value
            field = value
        }

    var startIcon: GenericImage? = null
        set(value) {
            binding.ivStartIcon.loadImage(value)
            binding.ivStartIcon.visibleIf(value != null)
            field = value
        }

    var endIcon: GenericImage? = null
        set(value) {
            binding.ivEndIcon.loadImage(value)
            binding.ivEndIcon.visibleIf(value != null)
            field = value
        }

    var text: String? = null
        set(value) {
            binding.tvTitle.text = value
            field = value
        }

    var isLoading: Boolean = false
        set(value) {
            setLoader(value)
            isEnabled = isEnabled
            field = value
        }

    var isVisible: Boolean = true
        set(value) {
            binding.apply {
                container.visibleIf(value)
                root.visibleIf(value)
            }
            field = value
        }

    init {
        isClickable = true
        isFocusable = true
        context.withStyledAttributes(attrs, R.styleable.ButtonView) {
            buttonSize = ButtonSize.getEnumForValue(
                getInt(R.styleable.ButtonView_btnSize, 0)
            )
            buttonType = ButtonType.getEnumForValue(
                getInt(R.styleable.ButtonView_btnType, 0)
            )
            startIcon = GenericImage.Resource(
                getResourceId(R.styleable.ButtonView_btnStartIcon, -1).takeIf { it != -1 }
            )
            endIcon = GenericImage.Resource(
                getResourceId(R.styleable.ButtonView_btnEndIcon, -1).takeIf { it != -1 }
            )
            text = getString(R.styleable.ButtonView_btnText)

            btnEnabled = getBoolean(R.styleable.ButtonView_btnEnabled, true)
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (!isLoading) {
            updateContentColor(enabled)
            children.forEach {
                enableViews(enabled, it)
            }
        }
    }

    private fun enableViews(enabled: Boolean, view: View) {
        view.isEnabled = enabled
        when (view) {
            is ViewGroup -> {
                view.children.forEach { childView ->
                    enableViews(enabled, childView)
                }
            }

            is AppCompatImageView -> view.alpha = if (enabled) 1f else 0.25f
        }
    }

    private fun updateColors() {
        binding.container.setBackgroundDrawable(buttonType.backgroundSelector)
        binding.loader.setAnimation(buttonType.loader)
        updateContentColor(isEnabled)
    }

    private fun updateContentColor(enabled: Boolean) {

        val colorContent = if (enabled) {
            ContextCompat.getColor(context, buttonType.colorEnabledContent)
        } else {
            ContextCompat.getColor(context, buttonType.colorDisabledContent)
        }

        binding.ivEndIcon.setTintColor(color = colorContent)
        binding.ivStartIcon.setTintColor(color = colorContent)
        binding.tvTitle.setTextColor(color = colorContent)

    }

    private fun updateSize() {
        val layoutHeight = context.resources.getDimensionPixelSize(buttonSize.height)
        val iconSize = context.resources.getDimensionPixelSize(buttonSize.iconSize)
        val outPadding = context.resources.getDimensionPixelSize(buttonSize.outPadding)
        val inPadding = context.resources.getDimensionPixelSize(buttonSize.inPadding)

        with(binding) {
            container.updatePadding(left = outPadding, right = outPadding)

            val startLayoutParams = ivStartIcon.layoutParams as LinearLayout.LayoutParams
            startLayoutParams.marginEnd = inPadding
            ivStartIcon.layoutParams = startLayoutParams

            val endLayoutParams = ivEndIcon.layoutParams as LinearLayout.LayoutParams
            endLayoutParams.marginStart = inPadding
            ivEndIcon.layoutParams = endLayoutParams

            container.layoutParams.height = layoutHeight
            ivStartIcon.layoutParams.width = iconSize
            ivStartIcon.layoutParams.height = iconSize
            ivEndIcon.layoutParams.width = iconSize
            ivEndIcon.layoutParams.height = iconSize
            tvTitle.setTextAppearance(context, buttonSize.textStyle)

            binding.loader.layoutParams.height = iconSize
            binding.loader.layoutParams.width = iconSize
        }
    }


    private fun setLoader(loader: Boolean) {
        val loaderAnim = if (isEnabled) buttonType.loader else R.raw.loader_secondary
        binding.loader.setAnimation(loaderAnim)
        binding.loader.playAnimation()
        isClickable = !loader
        binding.tvTitle.visibleIf(!loader, gone = false)
        binding.loader.visibleIf(loader)
        if (!loader) binding.loader.setAnimation(buttonType.loader)
    }

    enum class ButtonSize(
        val value: Int,
        @DimenRes val height: Int,
        @DimenRes val iconSize: Int,
        @DimenRes val outPadding: Int,
        @DimenRes val inPadding: Int,
        @StyleRes val textStyle: Int
    ) {
        Large(
            value = 0,
            height = R.dimen.size56,
            iconSize = R.dimen.size24,
            outPadding = R.dimen.size24,
            inPadding = R.dimen.size12,
            textStyle = R.style.TextButton1
        ),
        Medium(
            value = 1,
            height = R.dimen.size40,
            iconSize = R.dimen.size20,
            outPadding = R.dimen.size20,
            inPadding = R.dimen.size12,
            textStyle = R.style.TextButton2
        ),
        Small(
            value = 2,
            height = R.dimen.size32,
            iconSize = R.dimen.size16,
            outPadding = R.dimen.size12,
            inPadding = R.dimen.size8,
            textStyle = R.style.TextButton3
        );

        companion object {
            fun getEnumForValue(value: Int): ButtonSize {
                return entries.firstOrNull { it.value == value } ?: Large
            }
        }
    }

    enum class ButtonType(
        val value: Int,
        @DrawableRes val backgroundSelector: Int,
        @ColorRes val colorEnabledContent: Int,
        @ColorRes val colorDisabledContent: Int,
        @RawRes val loader: Int
    ) {
        Primary(
            value = 0,
            backgroundSelector = R.drawable.selector_button_view_primary,
            colorEnabledContent = R.color.invertedLight,
            colorDisabledContent = R.color.textDisabled,
            loader = R.raw.loader_primary
        ),
        Secondary(
            value = 1,
            backgroundSelector = R.drawable.selector_button_view_secondary,
            colorEnabledContent = R.color.textPrimary,
            colorDisabledContent = R.color.textDisabled,
            loader = R.raw.loader_secondary
        ),
        Negative(
            value = 2,
            backgroundSelector = R.drawable.selector_button_view_error,
            colorEnabledContent = R.color.errorBase,
            colorDisabledContent = R.color.errorPressed,
            loader = R.raw.loader_secondary
        );

        companion object {
            fun getEnumForValue(value: Int): ButtonType {
                return entries.firstOrNull { it.value == value } ?: Primary
            }
        }
    }


}