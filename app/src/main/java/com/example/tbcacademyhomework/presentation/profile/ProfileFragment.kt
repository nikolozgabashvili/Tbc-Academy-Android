package com.example.tbcacademyhomework.presentation.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentProfileBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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
            profileViewModel.logOutUser()
        }

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.event.collectLatest {
                    navController.navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.state.collectLatest {
                    binding.tvEmail.text = it.email
                }
            }
        }

    }

}