package com.example.tbcacademyhomework.login

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
import com.example.tbcacademyhomework.models.UserParams
import com.example.tbcacademyhomework.register.RegisterFragment
import com.example.tbcacademyhomework.storage.DatastoreImpl
import com.example.tbcacademyhomework.util.getError
import com.example.tbcacademyhomework.util.getParcelable
import com.example.tbcacademyhomework.util.isError
import com.example.tbcacademyhomework.util.isLoading
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()

        initListeners()
        setupResultListener()
        initObservers()


    }


    private fun initViewModel() {
        loginViewModel = ViewModelProvider(
            this, ViewModelFactory {
                LoginViewModel(DatastoreImpl(requireContext().applicationContext))
            }
        )[LoginViewModel::class.java]
    }


    private fun initListeners() {
        with(binding) {

            checkRemember.setOnCheckedChangeListener { _, isChecked ->
                loginViewModel.setCheckboxState(isChecked)
            }

            etEmail.doAfterTextChanged {
                loginViewModel.setUserEmail(it.toString())
            }

            etPassword.doAfterTextChanged {
                loginViewModel.setUserPassword(it.toString())
            }

            btnLogin.setOnClickListener {
                loginViewModel.loginUser()
            }

            btnRegister.setOnClickListener {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }


    }

    private fun setupResultListener() {
        setFragmentResultListener(RegisterFragment.RESULT_KEY) { _, bundle ->
            val userData = getParcelable(bundle, RegisterFragment.USER_DATA, UserParams::class.java)
            userData?.let { data ->
                binding.etPassword.setText(data.password)
                binding.etEmail.setText(data.email)
            }

        }

    }


    private fun initObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginScreenState.collect {
                    val resource = it.authResource
                    binding.btnLogin.isEnabled = it.isUserValid
                    binding.btnLogin.isLoading = resource.isLoading()
                    binding.btnRegister.btnEnabled = !resource.isLoading()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.navigationFlow.collect {email->
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                            email
                        )
                    )
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.responseFlow.collect { resource ->
                    if (resource.isError()) {
                        val error =
                            resource.errorMessage
                                ?: resource.errorType?.getError(requireContext())
                        error?.let {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }
}