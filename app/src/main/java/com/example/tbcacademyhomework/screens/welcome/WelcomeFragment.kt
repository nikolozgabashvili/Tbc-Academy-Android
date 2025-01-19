package com.example.tbcacademyhomework.screens.welcome

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentWelcomeBinding


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    private lateinit var navController: NavController

    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnRegister.setOnClickListener {
                navigateToRegister()
            }

            btnLogin.setOnClickListener {
                navigateToLogin()
            }
        }
    }


    private fun navigateToRegister() {
        navController.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment())

    }

    private fun navigateToLogin() {
        navController.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment())
    }

}