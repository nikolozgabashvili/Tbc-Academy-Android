package com.example.tbcacademyhomework.register

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentRegisterBinding
import com.example.tbcacademyhomework.models.UserParams
import com.example.tbcacademyhomework.util.getError
import com.example.tbcacademyhomework.util.isError
import com.example.tbcacademyhomework.util.isLoading
import com.example.tbcacademyhomework.util.isSuccess
import kotlinx.coroutines.launch


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
                registerViewModel.setEmail(it.toString())
            }

            etPassword.doAfterTextChanged {
                registerViewModel.setPassword(it.toString())
            }

            etRepeatPassword.doAfterTextChanged {
                registerViewModel.setRepeatPassword(it.toString())
            }

            btnBack.setOnClickListener {
                navController.popBackStack()
            }

            btnRegister.setOnClickListener {
                registerViewModel.registerUser()
            }


        }
    }



    private fun goBack() {
        val email = registerViewModel.registerScreenState.value.email
        val password = registerViewModel.registerScreenState.value.password
        val userBundle = Bundle().apply {
            putParcelable(USER_DATA, UserParams(email = email, password = password))
        }
        setFragmentResult(RESULT_KEY, userBundle)
        navController.navigateUp()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.registerScreenState.collect { state ->
                    binding.btnRegister.btnEnabled = state.isUserValid

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.responseFlow.collect { resource ->
                    binding.btnRegister.isLoading = resource.isLoading()
                    if (resource.isSuccess()) {
                        goBack()
                    } else if (resource.isError()) {
                        val error =
                            resource.errorMessage ?: resource.errorType?.getError(requireContext())

                        error?.let {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }


    companion object {
        const val RESULT_KEY = "result_key"
        const val USER_DATA = "user_data"
    }
}