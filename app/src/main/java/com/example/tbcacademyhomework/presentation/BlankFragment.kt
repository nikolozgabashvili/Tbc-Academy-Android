package com.example.tbcacademyhomework.presentation

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentBlankBinding
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
            binding.et2.endIcon = R.drawable.ic_visibility_off
            binding.et2.startIcon = R.drawable.ic_visibility_off

        }

        binding.btn.setOnClickListener {
            println(binding.et1.text)
            println(binding.et2.text)
            println(binding.et3.text)
        }


    }
}