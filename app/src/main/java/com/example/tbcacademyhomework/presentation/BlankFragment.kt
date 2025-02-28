package com.example.tbcacademyhomework.presentation

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.tbcacademyhomework.databinding.FragmentBlankBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class BlankFragment : BaseFragment<FragmentBlankBinding>(FragmentBlankBinding::inflate) {

    override fun init(savedInstanceState: Bundle?) {

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            binding.et2.endIcon = null
            binding.et2.text = "Dasd"
            binding.et2.startIcon = null
            delay(1000)

        }

        binding.btn.setOnClickListener {
            binding.btn2.isLoading = true
        }
        binding.btn3.setOnClickListener {
            binding.btn3.text = "Dasd"
            binding.btn2.isLoading = false
        }





    }
}