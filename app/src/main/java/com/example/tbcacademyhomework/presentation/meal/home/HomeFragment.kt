package com.example.tbcacademyhomework.presentation.meal.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.FragmentHomeBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.core.util.visibleIf
import com.example.tbcacademyhomework.presentation.meal.adapters.MealAdapter
import com.example.tbcacademyhomework.presentation.meal.home.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val categoryAdapter by lazy { CategoryAdapter(viewModel::onCategorySelected) }
    private val mealAdapter by lazy { MealAdapter(::onMealSelected) }
    override fun init() {

        initCategoryRecycler()
        initMealRecycler()
        initObservers()

    }


    private fun initCategoryRecycler() {
        binding.rvCategory.apply {
            itemAnimator = null
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

    private fun initMealRecycler() {
        binding.rvMeals.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = mealAdapter
        }
    }


    private fun initObservers() {
        launchCoroutineScope {
            viewModel.state.collectLatest { state ->
                categoryAdapter.submitList(state.mappedCategories)
                mealAdapter.submitList(state.mealsToShow)
                handleLoading(state)

            }
        }
    }

    private fun handleLoading(state: HomeScreenState) {
        with(binding) {
            rvCategory.visibleIf(!state.categoryLoading)
            categoryLoader.visibleIf(state.categoryLoading)
            rvMeals.visibleIf(!state.mealLoading)
            mealLoader.visibleIf(state.mealLoading)
        }
    }

    private fun onMealSelected(id: String) {
        //todo navigate

    }

}