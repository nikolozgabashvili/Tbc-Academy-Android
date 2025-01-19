package com.example.tbcacademyhomework.screens.login

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
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
            loginUser(email, password)
        }


    }

    private fun loginUser(email: String, password: String) {
        viewModel.loginUser(email, password)
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginResponse.collect { response ->
                    if (response != null) {
                        Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorFlow.collect { response ->
                    Toast.makeText(requireContext(), response.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}