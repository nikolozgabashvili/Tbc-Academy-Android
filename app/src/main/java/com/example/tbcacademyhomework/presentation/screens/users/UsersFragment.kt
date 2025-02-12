package com.example.tbcacademyhomework.presentation.screens.users

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.App
import com.example.tbcacademyhomework.databinding.FragmentUsersBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.factory.ViewModelFactory
import com.example.tbcacademyhomework.presentation.screens.users.adapters.LoaderStateAdapter
import com.example.tbcacademyhomework.presentation.screens.users.adapters.UsersAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var navController: NavController

    private val args: UsersFragmentArgs by navArgs()
    private val usersAdapter by lazy { UsersAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersViewModel = ViewModelProvider(
            this, factory = ViewModelFactory {
                UsersViewModel(
                    usersRemoteSource = App.appModule.usersRemoteSource,
                    connectivityObserver = App.appModule.connectivityObserver,
                    appDatabase = App.database

                )
            }
        )[UsersViewModel::class.java]
    }

    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
        setupRecycler()
        initObservers()
        initListeners()

    }

    private fun setupRecycler() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = usersAdapter.withLoadStateFooter(
            footer = LoaderStateAdapter()
        )
    }

    private fun initListeners() {
        binding.btnProfile.setOnClickListener {
            navController.navigate(UsersFragmentDirections.actionUsersFragmentToProfileFragment(args.email))
        }

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                usersViewModel.usersFlow.collectLatest { pagingData ->

                    usersAdapter.submitData(pagingData)
                }
            }
        }
    }

}