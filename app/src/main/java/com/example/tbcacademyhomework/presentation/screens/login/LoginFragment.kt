package com.example.tbcacademyhomework.presentation.screens.login

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.App
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
import com.example.tbcacademyhomework.domain.utils.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.factory.ViewModelFactory
import com.example.tbcacademyhomework.presentation.screens.register.RegisterFragment.Companion.RESULT_KEY
import com.example.tbcacademyhomework.presentation.screens.register.RegisterFragment.Companion.USER_DATA
import com.example.tbcacademyhomework.presentation.screens.register.RegisterParams
import com.example.tbcacademyhomework.presentation.utils.getParcelable
import com.example.tbcacademyhomework.presentation.utils.getValue
import com.example.tbcacademyhomework.presentation.utils.showToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(
            this, ViewModelFactory {
                LoginViewModel(
                    authRepository = App.appModule.authRepository,
                    userPrefsDataSource = App.appModule.userPrefsDataSource,
                    emailValidator = App.appModule.emailValidator,
                    passwordValidator = App.appModule.inputValidator
                )
            }
        )[LoginViewModel::class.java]
    }

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