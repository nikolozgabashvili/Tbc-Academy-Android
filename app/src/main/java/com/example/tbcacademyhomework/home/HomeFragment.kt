package com.example.tbcacademyhomework.home

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentHomeBinding
import com.example.tbcacademyhomework.login.ViewModelFactory
import com.example.tbcacademyhomework.storage.DatastoreImpl
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var homeViewModel: HomeViewModel
    private val args: HomeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(
            this, ViewModelFactory {
                HomeViewModel(DatastoreImpl(requireContext().applicationContext))
            }
        )[HomeViewModel::class.java]
    }

    override fun init(savedInstanceState: Bundle?) {
        initListeners()
        initViews()

    }

    private fun initViews() {
        binding.tvEmail.text = args.email
    }


    private fun initListeners() {
        binding.btnLogOut.setOnClickListener {
            homeViewModel.logOut()
            initObservers()
        }

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.getUserMail().collect { email ->
                    if (email == null) {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                    }
                }
            }
        }
    }

}