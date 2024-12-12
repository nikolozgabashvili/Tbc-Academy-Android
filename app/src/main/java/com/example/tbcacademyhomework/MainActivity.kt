package com.example.tbcacademyhomework

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.tbcacademyhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()

        initScreen()


    }

    private fun initScreen() {
        binding.tvLocationName.text = getString(R.string.location_name)
        binding.tvLocation.text = getString(R.string.location)
        binding.tvOverview.text = getString(R.string.overview)
        binding.tvDetails.text = getString(R.string.details)
        binding.tvTime.text = getString(R.string.time)
        binding.tvTemperature.text = getString(R.string.temperature)
        binding.tvRating.text = getString(R.string.rating)
        binding.btnBook.text = getString(R.string.book_now)
        binding.tvPriceKey.text = getString(R.string.price)
        binding.tvDescription.text = getString(R.string.description)
        setPrice(getString(R.string.price_value))

    }

    private fun setPrice(text: String) {
        if (text.startsWith("$")) {
            val spannableStringBuilder = SpannableStringBuilder(text).apply {
                setSpan(
                    ForegroundColorSpan(getColor(R.color.dollar_color)),
                    0,
                    1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(AbsoluteSizeSpan(20, true), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            binding.tvPrice.text = spannableStringBuilder
        }else{
            binding.tvPrice.text = text
        }
    }

    private fun handleEdgeToEdge() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding = if (imeInsets.bottom == 0) systemBars.bottom else imeInsets.bottom
            view.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = bottomPadding
            )
            insets
        }
    }
}