package com.example.tbcacademyhomework.presentation.register

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentRegisterBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.ext.observeEvent
import com.example.tbcacademyhomework.presentation.ext.observeState
import com.example.tbcacademyhomework.presentation.ext.showSnackBar
import com.example.tbcacademyhomework.presentation.utils.getValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var navController: NavController


    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        initListeners()
        initObservers()


    }

    private fun initListeners() {
        with(binding) {
            etEmail.doAfterTextChanged {
                registerViewModel.onAction(
                    RegisterScreenAction.OnUserInput(
                        email = it.toString(),
                        password = etPassword.text.toString(),
                        repeatPassword = etRepeatPassword.text.toString()
                    )
                )
            }

            etPassword.doAfterTextChanged {
                registerViewModel.onAction(
                    RegisterScreenAction.OnUserInput(
                        email = etEmail.text.toString(),
                        password = it.toString(),
                        repeatPassword = etRepeatPassword.text.toString()
                    )
                )
            }

            etRepeatPassword.doAfterTextChanged {
                registerViewModel.onAction(
                    RegisterScreenAction.OnUserInput(
                        email = etEmail.text.toString(),
                        password = etPassword.text.toString(),
                        repeatPassword = it.toString()
                    )
                )
            }

            btnRegister.setOnClickListener {
                registerViewModel.onAction(
                    RegisterScreenAction.OnRegister(
                        email = etEmail.text.toString(),
                        password = etPassword.text.toString()
                    )
                )
            }

            btnBack.setOnClickListener {
                navController.navigateUp()
            }
        }
    }

    private fun initObservers() {

        observeEvent(registerViewModel.event) { event ->
            when (event) {
                is RegisterEvent.Error -> binding.root.showSnackBar(
                    event.error.getValue(requireContext())
                )

                is RegisterEvent.Success -> {

                    goToLogin(event.params)
                }
            }
        }


        observeState(registerViewModel.state) { state ->
            handleLoading(state.isLoading)
            binding.btnRegister.btnEnabled = state.isUserValid
        }
    }

    private fun handleLoading(loading: Boolean) {
        with(binding) {
            btnRegister.isLoading = loading
            etEmail.isEnabled = !loading
            etPassword.isEnabled = !loading
            etRepeatPassword.isEnabled = !loading
            btnBack.isEnabled = !loading
        }


    }

    private fun goToLogin(params: RegisterParams) {
        val bundle = Bundle().apply {
            putParcelable(USER_DATA, params)
        }
        setFragmentResult(RESULT_KEY, bundle)
        navController.navigateUp()

    }

    companion object {
        const val RESULT_KEY = "result_key"
        const val USER_DATA = "user_data"
    }

}