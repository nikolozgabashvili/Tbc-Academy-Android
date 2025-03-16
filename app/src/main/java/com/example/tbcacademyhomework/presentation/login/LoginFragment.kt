package com.example.tbcacademyhomework.presentation.login

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.BuildConfig
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.register.RegisterFragment.Companion.RESULT_KEY
import com.example.tbcacademyhomework.presentation.register.RegisterFragment.Companion.USER_DATA
import com.example.tbcacademyhomework.presentation.register.RegisterParams
import com.example.tbcacademyhomework.presentation.utils.ext.observeEvent
import com.example.tbcacademyhomework.presentation.utils.ext.observeState
import com.example.tbcacademyhomework.presentation.utils.ext.showSnackBar
import com.example.tbcacademyhomework.presentation.utils.getParcelable
import com.example.tbcacademyhomework.presentation.utils.getValue
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var navController: NavController

    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        initListeners()
        initObservers()
        loadDebugData()

    }

    private fun initListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                loginViewModel.onAction(
                    LoginScreenAction.OnLogin(
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        rbRememberMe.isChecked
                    )
                )
            }

            btnRegister.setOnClickListener {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            etEmail.doAfterTextChanged {
                loginViewModel.onAction(
                    LoginScreenAction.OnInputChanged(
                        it.toString(),
                        etPassword.text.toString()
                    )
                )
            }

            etPassword.doAfterTextChanged {
                loginViewModel.onAction(
                    LoginScreenAction.OnInputChanged(
                        etEmail.text.toString(),
                        it.toString()
                    )
                )
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

        observeState(loginViewModel.state) { state ->
            handleLoading(state.isLoading)
            binding.btnLogin.btnEnabled = state.isUserValid
        }

        observeEvent(loginViewModel.event) { event ->
            when (event) {
                is LoginEvent.Success -> {
                    navController.navigate(
                        LoginFragmentDirections.actionLoginFragmentToUsersFragment()
                    )
                }
                is LoginEvent.Error -> {
                    binding.root.showSnackBar(event.error.getValue(requireContext()))
                }
            }
        }
    }

    private fun loadDebugData() {
        if (BuildConfig.DEBUG) {
            binding.etEmail.setText(getString(R.string.test_dummy_email))
            binding.etPassword.setText(getString(R.string.test_dummy_password))
        }
    }

    private fun handleLoading(loading: Boolean) {
        with(binding) {
            btnLogin.isLoading = loading
            btnLogin.btnEnabled = loading
            btnRegister.isEnabled = !loading
            binding.rbRememberMe.isEnabled = !loading
            etEmail.isEnabled = !loading
            etPassword.isEnabled = !loading
        }

    }


}