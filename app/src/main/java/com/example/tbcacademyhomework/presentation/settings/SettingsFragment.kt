package com.example.tbcacademyhomework.presentation.settings

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.FragmentSettingsBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.settings.adapter.SettingItemType
import com.example.tbcacademyhomework.presentation.settings.adapter.SettingItemsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val navController by lazy { findNavController() }
    private val settingsAdapter by lazy {
        SettingItemsAdapter(
            items = SettingItemType.entries,
            onItemClicked = ::settingItemClicked
        )
    }

    override fun init() {
        initSettingRecycler()

    }

    private fun initSettingRecycler() {
        binding.rvSettingItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = settingsAdapter
        }

    }

    private fun settingItemClicked(item: SettingItemType) {
        when (item) {
            SettingItemType.PROFILE -> {

            }

            SettingItemType.THEME -> {
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToThemeFragment())
            }

            SettingItemType.LANGUAGE -> {
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToLanguageFragment())

            }
        }
    }


}