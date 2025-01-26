package com.example.tbcacademyhomework.profile

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentProfileBinding
import com.example.tbcacademyhomework.login.ViewModelFactory
import com.example.tbcacademyhomework.storage.DatastoreImpl
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private lateinit var profileViewModel: ProfileViewModel
    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(
            this, ViewModelFactory {
                ProfileViewModel(DatastoreImpl(requireContext().applicationContext))
            }
        )[ProfileViewModel::class.java]
    }

    override fun init(savedInstanceState: Bundle?) {
        initListeners()
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.tvEmail.text = args.email
    }


    private fun initListeners() {
        binding.btnLogOut.setOnClickListener {
            profileViewModel.logOut()
        }

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.logOutFlow.collect {
                    findNavController().navigate(ProfileFragmentDirections.actionHomeFragmentToLoginFragment())
                }
            }
        }
    }

}