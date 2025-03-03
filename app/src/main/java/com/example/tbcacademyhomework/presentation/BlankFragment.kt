package com.example.tbcacademyhomework.presentation


import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentBlankBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment


class BlankFragment : BaseFragment<FragmentBlankBinding>(FragmentBlankBinding::inflate) {

    override fun init() {
        binding.btn.setOnClickListener {
            findNavController().navigate(BlankFragmentDirections.actionBlankFragmentToLanguageFragment())
        }



    }
}