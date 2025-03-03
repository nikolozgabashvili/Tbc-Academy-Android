package com.example.tbcacademyhomework.presentation.core.managers.language.screen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentLanguageBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::inflate) {

    private val languageViewModel: LanguageViewModel by viewModels()
    private val languageAdapter by lazy { LanguageAdapter(onLanguageSelected = languageViewModel::onLanguageSelected) }
    private val navController by lazy { findNavController() }

    override fun init() {
        initViews()
        initLanguageRecycler()
        initObservers()

    }

    private fun initViews() {
        with(binding.topBar) {
            btnStart.setOnClickListener {
                navController.navigateUp()
            }
            tvTitle.text = getString(R.string.language_change_title)

        }

    }

    private fun initLanguageRecycler() {
        binding.rvLanguage.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(requireContext())
            adapter = languageAdapter
        }

    }

    private fun initObservers() {
        launchCoroutineScope {
            languageViewModel.state.collectLatest { state ->
                languageAdapter.submitList(state.languages)
            }
        }

    }
}