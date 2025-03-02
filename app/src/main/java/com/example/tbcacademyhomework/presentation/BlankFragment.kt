package com.example.tbcacademyhomework.presentation

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentBlankBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class BlankFragment : BaseFragment<FragmentBlankBinding>(FragmentBlankBinding::inflate) {

    override fun init() {

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            binding.et2.endIcon = null
            binding.et2.text = "Dasd"
            binding.et2.startIcon = null
            delay(1000)

        }

        binding.btn.setOnClickListener {
            findNavController().navigate(BlankFragmentDirections.actionBlankFragmentToThemeFragment())
        }
        binding.btn3.setOnClickListener {
            findNavController().navigate(BlankFragmentDirections.actionBlankFragmentToLanguageFragment())
        }





    }
}