package com.example.tbcacademyhomework.presentation.core.managers.theme

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentThemeBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ThemeFragment : BaseFragment<FragmentThemeBinding>(FragmentThemeBinding::inflate) {

    private val themeViewModel: ThemeViewModel by viewModels()
    private val themeAdapter by lazy { ThemeAdapter(onThemeSelected = themeViewModel::onThemeSelected) }
    private val navController by lazy { findNavController() }

    override fun init(savedInstanceState: Bundle?) {
        initViews()
        initThemeRecycler()
        initObservers()
    }

    private fun initViews() {
        with(binding.topBar) {
            btnStart.setOnClickListener {
                navController.navigateUp()
            }
            tvTitle.text = getString(R.string.day_night_theme)

        }

    }

    private fun initThemeRecycler() {
        binding.rvTheme.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(requireContext())
            adapter = themeAdapter
        }
    }

    private fun initObservers() {
        launchCoroutineScope {
            themeViewModel.state.collectLatest { state ->
                themeAdapter.submitList(state.themes)
            }
        }
    }


}