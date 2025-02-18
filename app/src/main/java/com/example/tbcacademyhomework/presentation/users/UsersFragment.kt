package com.example.tbcacademyhomework.presentation.users

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcacademyhomework.databinding.FragmentUsersBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.users.adapters.UsersAdapter
import com.example.tbcacademyhomework.presentation.utils.getValue
import com.example.tbcacademyhomework.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val usersViewModel: UsersViewModel by viewModels()
    private lateinit var navController: NavController

    private val usersAdapter by lazy { UsersAdapter() }


    override fun init(savedInstanceState: Bundle?) {
        navController = findNavController()
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

                    usersViewModel.fetchUsers()

                }
            }
        })

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                usersViewModel.state.collectLatest { state ->
                    handleState(state)


                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                usersViewModel.event.collect { event ->
                    when (event) {
                        is UserScreenEvent.Error -> showToast(
                            requireContext(),
                            event.error.getValue(requireContext())
                        )
                    }

                }
            }
        }
    }

    private fun handleState(state: UsersScreenState) {
        usersAdapter.submitList(state.users)
        binding.loader1.isVisible = state.loading
    }

}