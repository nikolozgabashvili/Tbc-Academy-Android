package com.example.tbcacademyhomework.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentHomeBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.home.adapters.EndPaddingDecorator
import com.example.tbcacademyhomework.presentation.home.adapters.PostAdapter
import com.example.tbcacademyhomework.presentation.home.adapters.StoryAdapter
import com.example.tbcacademyhomework.presentation.util.ScreenEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val storyAdapter by lazy { StoryAdapter() }
    private val postAdapter by lazy { PostAdapter() }

    override fun init(savedInstanceState: Bundle?) {
        initStoryRecycler()
        initPostRecycler()
        initObservers()


    }

    private fun initStoryRecycler() {
        binding.rvStories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvStories.addItemDecoration(EndPaddingDecorator(resources.getDimensionPixelSize(R.dimen.size19)))
        binding.rvStories.adapter = storyAdapter

    }

    private fun initPostRecycler() {
        with(binding) {
            rvPosts.layoutManager = LinearLayoutManager(requireContext())
            rvPosts.adapter = postAdapter
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.event.collectLatest { event ->
                    when (event) {
                        ScreenEvent.Success -> {

                        }

                        is ScreenEvent.Error -> {
                            val error = event.error.getValue(requireContext())
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.state.collectLatest { state ->
                    with(binding) {
                        loader.isVisible = state.isStoriesLoading || state.isPostsLoading
                        rvStories.isVisible = !state.isStoriesLoading
                        rvPosts.isVisible = !state.isPostsLoading
                    }
                    storyAdapter.submitList(state.stories)
                    postAdapter.submitList(state.posts)

                }
            }
        }


    }
}