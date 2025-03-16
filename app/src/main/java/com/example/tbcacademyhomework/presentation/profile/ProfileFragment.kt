package com.example.tbcacademyhomework.presentation.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentProfileBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.utils.ext.observeEvent
import com.example.tbcacademyhomework.presentation.utils.ext.observeState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var navController: NavController


    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        initListeners()
        initObservers()
    }


    private fun initListeners() {
        binding.btnLogOut.setOnClickListener {
            profileViewModel.onAction(ProfileScreenAction.OnLogout)
        }

    }

    private fun initObservers() {
        observeEvent(profileViewModel.event) {
            navController.navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
        }

        observeState(profileViewModel.state) {
            binding.tvEmail.text = it.email
        }
    }

}