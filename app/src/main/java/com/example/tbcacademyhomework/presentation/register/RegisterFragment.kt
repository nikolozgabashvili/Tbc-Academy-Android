package com.example.tbcacademyhomework.presentation.register

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentRegisterBinding
import com.example.tbcacademyhomework.domain.utils.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.utils.getValue
import com.example.tbcacademyhomework.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
                registerViewModel.updateEmail(it.toString())
            }

            etPassword.doAfterTextChanged {
                registerViewModel.updatePassword(it.toString())
            }

            etRepeatPassword.doAfterTextChanged {
                registerViewModel.updateRepeatPassword(it.toString())
            }

            btnRegister.setOnClickListener {
                registerViewModel.registerUser()
            }

            btnBack.setOnClickListener {
                navController.navigateUp()
            }
        }
    }

    private fun initObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.event.collect { event ->
                    when (event) {
                        is RegisterEvent.Error -> showToast(
                            requireContext(),
                            event.error.getValue(requireContext())
                        )

                        is RegisterEvent.Success -> {

                            goToLogin(event.params)
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.state.collectLatest { state ->
                    binding.btnRegister.isLoading = state.registerResource?.isLoading() == true
                    binding.btnRegister.btnEnabled = state.isUserValid
                }
            }
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