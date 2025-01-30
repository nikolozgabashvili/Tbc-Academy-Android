package com.example.tbcacademyhomework.user.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbcacademyhomework.App
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.ViewModelFactory
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentUserBinding
import com.example.tbcacademyhomework.error.UserValidationError
import com.example.tbcacademyhomework.error.getErrorString
import kotlinx.coroutines.launch


class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private lateinit var userViewmodel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewmodel = ViewModelProvider(
            this,
            ViewModelFactory {
                UserViewModel(App.dataStore)
            }
        )[UserViewModel::class.java]
    }

    override fun init(savedInstanceState: Bundle?) {
        initListeners()
        initObservers()

    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            val user = UserUi(
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                email = binding.etEmail.text.toString()
            )
            userViewmodel.saveUser(user)
        }

        binding.btnRead.setOnClickListener {
            userViewmodel.getUser()
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewmodel.events.collect { event ->
                    when (event) {
                        is UserFragmentEvents.Error -> {
                            handleError(event.error)
                        }

                        UserFragmentEvents.Success -> {
                            handleSuccess()

                        }
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewmodel.fetchedUser.collect { user ->

                    if (user?.isValid == true) {
                        binding.tvEmail.text = getString(R.string.display_email, user.email)
                        binding.tvLastName.text =
                            getString(R.string.display_last_name, user.lastName)
                        binding.tvFirstName.text =
                            getString(R.string.display_first_name, user.firstName)
                    }

                }

            }
        }

    }

    private fun handleSuccess() {
        with(binding) {
            etEmail.setText("")
            etLastName.setText("")
            etFirstName.setText("")
            Toast.makeText(
                requireContext(),
                getString(R.string.user_saved), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun handleError(error: UserValidationError) {
        when (error) {
            UserValidationError.EMPTY_FIRST_NAME -> {
                binding.etFirstName.error =
                    error.getErrorString(requireContext())
            }

            UserValidationError.EMPTY_LAST_NAME -> {
                binding.etLastName.error =
                    error.getErrorString(requireContext())
            }

            UserValidationError.INVALID_EMAIL -> {
                binding.etEmail.error = error.getErrorString(requireContext())
            }
        }
    }

}