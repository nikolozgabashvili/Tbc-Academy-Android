package com.example.tbcacademyhomework.presentation.screen.category

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.FragmentCategoryBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.GenericString
import com.example.tbcacademyhomework.presentation.core.util.getValue
import com.example.tbcacademyhomework.presentation.core.util.observeState
import com.example.tbcacademyhomework.presentation.screen.category.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val viewmodel: CategoryViewModel by viewModels()
    private val categoryAdapter by lazy { CategoryAdapter() }


    override fun init(savedInstanceState: Bundle?) {
        initCategoryRecycler()
        listeners()
        observe()


    }

    private fun initCategoryRecycler() {
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }

    }

    private fun listeners() {
        binding.etSearch.doAfterTextChanged {
            viewmodel.onAction(CategoryScreenAction.OnQueryChange(it.toString()))
        }

        binding.itemError.btnRetry.setOnClickListener {
            viewmodel.onAction(CategoryScreenAction.OnRetry)
        }

    }

    private fun observe() {

        observeState(viewmodel.state) { state ->
            handleRecycler(state)

            handleLoading(state.isLoading)
            handleError(state.error)

        }


    }

    private fun handleRecycler(state: CategoryScreenState) {
        binding.rvCategories.isVisible = state.showList
        categoryAdapter.submitList(state.categories)
    }

    private fun handleError(error: GenericString?) {
        binding.itemError.root.isVisible = error != null
        binding.itemError.tvError.text = error?.getValue(requireContext())


    }

    private fun handleLoading(loading: Boolean) {
        binding.progress.isVisible = loading

    }

}