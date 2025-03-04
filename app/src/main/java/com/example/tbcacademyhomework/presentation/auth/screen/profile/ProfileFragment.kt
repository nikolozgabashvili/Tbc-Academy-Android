package com.example.tbcacademyhomework.presentation.auth.screen.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentProfileBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun init() {
        initViews()
        initObservers()
        initListeners()

    }

    private fun initListeners() {
        binding.topBar.btnStart.setOnClickListener {
            navController.navigateUp()
        }
        binding.btnLogOut.setOnClickListener {
            viewModel.signOut()
        }
    }

    private fun initViews() {

        binding.topBar.tvTitle.text = getString(R.string.profile)
    }

    private fun initObservers() {
        launchCoroutineScope {
            viewModel.events.collectLatest {
                when (it) {

                    ProfileScreenEvent.SignOut -> {
                        Glide.get(requireContext()).clearMemory()
                        launchCoroutineScope(dispatcher = Dispatchers.IO) {
                            Glide.get(requireContext()).clearDiskCache()
                        }

                        navController.navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())

                    }
                }
            }
        }

        launchCoroutineScope {
            viewModel.state.collectLatest {
                binding.tvEmail.text = it.email
            }
        }

    }

}