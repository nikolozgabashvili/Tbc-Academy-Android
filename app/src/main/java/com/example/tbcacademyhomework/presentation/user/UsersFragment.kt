package com.example.tbcacademyhomework.presentation.user

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.App
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentUsersBinding
import com.example.tbcacademyhomework.presentation.factory.ViewModelFactory
import com.example.tbcacademyhomework.util.NetworkManager
import com.example.tbcacademyhomework.util.getError
import com.example.tbcacademyhomework.util.isError
import kotlinx.coroutines.launch


class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {


    private lateinit var usersViewModel: UsersViewModel
    private val usersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        usersViewModel = ViewModelProvider(
            this, ViewModelFactory {
                UsersViewModel(
                    App.database,
                    NetworkManager(requireContext().applicationContext)
                )
            }
        )[UsersViewModel::class.java]
    }

    override fun init(savedInstanceState: Bundle?) {
        initRecycler()
        initObservers()

    }

    private fun initRecycler() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = usersAdapter

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                usersViewModel.state.collect { state ->
                    val users = state.users
                    handleLoading(state.isFetching)
                    handlePlaceholder(state.showPlaceholder)
                    if (users.isNotEmpty()) {
                        usersAdapter.submitList(users)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                usersViewModel.event.collect { event ->
                    when (event) {
                        UserEvents.NoNetwork -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.you_are_offline), Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        UserEvents.YesNetwork -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.you_are_online), Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        is UserEvents.Error -> {
                            val error = event.error
                            if (error.isError()) {
                                Toast.makeText(
                                    requireContext(),
                                    error.errorMessage
                                        ?: error.errorType?.getError(requireContext()),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handlePlaceholder(placeholderVisibility: Boolean) {
        binding.itemPlaceholder.root.isVisible = placeholderVisibility
    }

    private fun handleLoading(loading: Boolean) {
        binding.loading.isVisible = loading
    }


}