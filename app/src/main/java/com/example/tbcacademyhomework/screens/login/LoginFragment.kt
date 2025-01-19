package com.example.tbcacademyhomework.screens.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
import com.example.tbcacademyhomework.network.models.isError
import com.example.tbcacademyhomework.network.models.isSuccess
import com.example.tbcacademyhomework.util.showToast
import com.example.tbcacademyhomework.util.toErrorString
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    override fun init(savedInstanceState: Bundle?) {
        initListeners()
        initObserver()

    }


    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            validateUserInputs()
        }
    }

    private fun validateUserInputs() {
        with(binding) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val emailValidation = viewModel.checkEmail(email)
            val passwordValidation = viewModel.checkPassword(password)

            if (emailValidation == null && passwordValidation == null)
                viewModel.loginUser(email, password)
            else {
                etEmail.error = emailValidation.toErrorString(requireContext())
                etPassword.error = passwordValidation.toErrorString(requireContext())
            }
        }


    }

    private fun clearInputs() {
        binding.etEmail.setText("")
        binding.etPassword.setText("")
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginResponse.collect { response ->
                    if (response.isSuccess()) {
                        clearInputs()
                        showToast(
                            requireContext(),
                            getString(R.string.success))
                    } else if (response.isError()) {
                        showToast(requireContext(), response?.errorMessage)
                    }
                }
            }
        }

    }



}