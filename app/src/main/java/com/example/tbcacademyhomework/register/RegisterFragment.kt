package com.example.tbcacademyhomework.register

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.adapters.InputGroupAdapter
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewmodel by viewModels<RegisterViewModel>()

    private val inputGroupAdapter by lazy {
        InputGroupAdapter(
            viewmodel.getResponseList(),
            viewmodel::updateUserInputData
        )
    }

    override fun init(savedInstanceState: Bundle?) {

        initViews()
        setupRecycler()


    }

    private fun initViews() {
        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        viewmodel.validateData()?.let {
            Toast.makeText(requireContext(),
                getString(R.string.please_fill_in, it), Toast.LENGTH_SHORT).show()
        }?: run {
            Toast.makeText(requireContext(),
                getString(R.string.registered_successfully), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecycler() {
        binding.rvInputGroups.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInputGroups.adapter = inputGroupAdapter
    }


}