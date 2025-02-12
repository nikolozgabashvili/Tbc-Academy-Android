package com.example.tbcacademyhomework.presentation.screens.profile

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbcacademyhomework.App
import com.example.tbcacademyhomework.databinding.FragmentProfileBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.factory.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private lateinit var profileViewModel: ProfileViewModel
    private val args: ProfileFragmentArgs by navArgs()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel = ViewModelProvider(
            this, ViewModelFactory {
                ProfileViewModel(
                    App.appModule.userPrefsDataSource,
                    App.database
                )
            }
        )[ProfileViewModel::class.java]

    }

    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        initListeners()
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.event.collectLatest {
                    navController.navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
                }
            }
        }

    }

    private fun initViews() {
        binding.tvEmail.text = args.email
    }

    private fun initListeners() {
        binding.btnLogOut.setOnClickListener {
            profileViewModel.logOutUser()
        }

    }

}