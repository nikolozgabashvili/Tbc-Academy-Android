package com.example.tbcacademyhomework.screens.register

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentRegisterBinding
import com.example.tbcacademyhomework.network.models.isError
import com.example.tbcacademyhomework.network.models.isSuccess
import com.example.tbcacademyhomework.util.showToast
import com.example.tbcacademyhomework.util.toErrorString
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun init(savedInstanceState: Bundle?) {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener {
            validateUserInputs()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerResponse.collect { response ->
                    if (response.isSuccess()) {
                        showToast(requireContext(), getString(R.string.success))
                    } else if (response.isError()) {
                        showToast(requireContext(), response?.errorMessage)
                    }

                }
            }
        }
    }

    private fun validateUserInputs() {
        with(binding) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val emailValidation = viewModel.checkEmail(email)
            val passwordValidation = viewModel.checkPassword(password)

            if (emailValidation == null && passwordValidation == null)
                viewModel.registerUser(email, password)
            else {
                etEmail.error = emailValidation.toErrorString(requireContext())
                etPassword.error = passwordValidation.toErrorString(requireContext())
            }
        }


    }


}