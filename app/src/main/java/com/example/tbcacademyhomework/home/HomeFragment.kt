package com.example.tbcacademyhomework.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewmodel: HomeViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()

    private val usersAdapter by lazy { UsersAdapter() }

    override fun init(savedInstanceState: Bundle?) {
        initListeners()
        setupRecycler()
        initObservers()

    }

    private fun initListeners() {
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProfileFragment(
                    args.email
                )
            )
        }
    }

    private fun setupRecycler() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = usersAdapter.withLoadStateFooter(
            footer = LoaderStateAdapter()
        )
    }


    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewmodel.userFlow.collectLatest { users ->
                    usersAdapter.submitData(pagingData = users)

                }

            }
        }
    }


}