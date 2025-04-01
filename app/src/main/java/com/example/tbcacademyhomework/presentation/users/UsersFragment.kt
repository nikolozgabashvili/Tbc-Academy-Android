package com.example.tbcacademyhomework.presentation.users

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.databinding.FragmentUsersBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.users.adapters.UsersAdapter
import com.example.tbcacademyhomework.presentation.users.util.UserScreenAction
import com.example.tbcacademyhomework.presentation.ext.observeEvent
import com.example.tbcacademyhomework.presentation.ext.observeState
import com.example.tbcacademyhomework.presentation.ext.showSnackBar
import com.example.tbcacademyhomework.presentation.utils.getValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val usersViewModel: UsersViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val usersAdapter by lazy { UsersAdapter() }


    override fun init(savedInstanceState: Bundle?) {
        setupRecycler()
        initObservers()
        initListeners()

    }

    private fun setupRecycler() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = usersAdapter
    }

    private fun initListeners() {
        binding.btnProfile.setOnClickListener {
            navController.navigate(UsersFragmentDirections.actionUsersFragmentToProfileFragment())
        }

        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val endReached = usersViewModel.state.value.pagingFinished
                val isLoading = usersViewModel.state.value.loading
                if (!endReached && totalItemCount <= (lastVisibleItem + 2) && !isLoading) {

                    usersViewModel.onAction(UserScreenAction.OnFetchRequest)

                }
            }
        })

    }

    private fun initObservers() {
        observeState(usersViewModel.state) { state ->
            handleState(state)


        }

        observeEvent(usersViewModel.event) { event ->
            when (event) {
                is UserScreenEvent.Error -> binding.root.showSnackBar(
                    event.error.getValue(requireContext())
                )
            }

        }
    }

    private fun handleState(state: UsersScreenState) {
        usersAdapter.submitList(state.users)
        binding.loader1.isVisible = state.loading
    }

}