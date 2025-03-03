package com.example.tbcacademyhomework.presentation.auth.screen.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
import com.example.tbcacademyhomework.presentation.auth.util.toErrorString
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.hide
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private val navController by lazy { findNavController() }


    override fun init() {
        initViews()
        initObservers()
        initListeners()

    }


    private fun initViews() {
        binding.topBar.btnStart.hide()
        binding.topBar.tvTitle.text = getString(R.string.login)
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text
            val password = binding.etPassword.text
            viewModel.onLoginClicked(
                email = email,
                password = password
            )
        }
        binding.btnRegister.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }


    private fun initObservers() {
        launchCoroutineScope {
            viewModel.state.collectLatest {
                binding.btnLogin.isLoading = it.loading
                binding.btnRegister.isEnabled = !it.loading

            }
        }

        launchCoroutineScope {
            viewModel.events.collect {
                when (it) {
                    LoginScreenEvents.NavigateHome -> {
                        navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    }

                    is LoginScreenEvents.Error -> {
                        binding.root.showSnackBar(it.firebaseError.toErrorString(requireContext()))
                    }

                    is LoginScreenEvents.InvalidInputs -> {
                        setInputErrors(it)
                    }
                }
            }
        }

    }

    private fun setInputErrors(it: LoginScreenEvents.InvalidInputs) {
        if (!it.passwordValid) {
            binding.etPassword.errorText = getString(R.string.empty_password_error)
            binding.etPassword.isError = true
        }
        if (!it.emailValid) {
            binding.etEmail.errorText = getString(R.string.invalid_email_error)
            binding.etEmail.isError = true
        }
    }
}