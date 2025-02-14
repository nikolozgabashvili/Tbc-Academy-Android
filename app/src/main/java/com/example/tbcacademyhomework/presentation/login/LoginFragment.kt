package com.example.tbcacademyhomework.presentation.login

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
import com.example.tbcacademyhomework.domain.utils.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.register.RegisterFragment.Companion.RESULT_KEY
import com.example.tbcacademyhomework.presentation.register.RegisterFragment.Companion.USER_DATA
import com.example.tbcacademyhomework.presentation.register.RegisterParams
import com.example.tbcacademyhomework.presentation.utils.getParcelable
import com.example.tbcacademyhomework.presentation.utils.getValue
import com.example.tbcacademyhomework.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var navController: NavController

    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        initListeners()
        initObservers()

    }

    private fun initListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                loginViewModel.loginUser()
            }

            btnRegister.setOnClickListener {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            checkRemember.setOnCheckedChangeListener { _, isChecked ->
                loginViewModel.updateCheckboxState(isChecked)
            }

            etEmail.doAfterTextChanged {
                loginViewModel.updateEmail(it.toString())
            }

            etPassword.doAfterTextChanged {
                loginViewModel.updatePassword(it.toString())
            }

        }

        setFragmentResultListener(RESULT_KEY) { _, bundle ->
            val registerParams = getParcelable(bundle, USER_DATA, RegisterParams::class.java)
            registerParams?.let { params ->
                binding.etPassword.setText(params.password)
                binding.etEmail.setText(params.email)
            }

        }

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.state.collectLatest { state ->
                    binding.btnLogin.isLoading = state.authResource?.isLoading() == true
                    binding.btnRegister.isEnabled = state.authResource?.isLoading() != true
                    binding.btnLogin.btnEnabled = state.isUserValid
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.event.collect { event ->
                    when (event) {
                        is LoginEvent.Success -> {
                            navController.navigate(
                                LoginFragmentDirections.actionLoginFragmentToUsersFragment(
                                    event.email
                                )
                            )
                        }

                        is LoginEvent.Error -> {
                            showToast(requireContext(), event.error.getValue(requireContext()))
                        }
                    }
                }
            }
        }

    }


}