package com.example.tbcacademyhomework.presentation.core.managers.theme

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.FragmentThemeBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ThemeFragment : BaseFragment<FragmentThemeBinding>(FragmentThemeBinding::inflate) {

    private val themeViewModel: ThemeViewModel by viewModels()
    private val themeAdapter by lazy { ThemeAdapter(onThemeSelected = themeViewModel::onThemeSelected) }

    override fun init(savedInstanceState: Bundle?) {
        initThemeRecycler()
        initObservers()
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