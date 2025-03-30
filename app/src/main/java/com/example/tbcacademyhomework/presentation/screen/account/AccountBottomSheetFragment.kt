package com.example.tbcacademyhomework.presentation.screen.account

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.FragmentAccountBottomSheetBinding
import com.example.tbcacademyhomework.presentation.base.BaseBottomSheetFragment
import com.example.tbcacademyhomework.presentation.common.extension.observeEvent
import com.example.tbcacademyhomework.presentation.common.extension.observeState
import com.example.tbcacademyhomework.presentation.common.extension.showSnackBar
import com.example.tbcacademyhomework.presentation.screen.account.adapter.AccountsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountBottomSheetFragment :
    BaseBottomSheetFragment<FragmentAccountBottomSheetBinding>(FragmentAccountBottomSheetBinding::inflate) {
    private val navController by lazy { findNavController() }
    private val accountsAdapter by lazy {
        AccountsAdapter(onClick = {
            onItemSelected(it)
        })
    }

    private val viewModel: AccountViewModel by viewModels()


    override fun init() {
        setupRecycler()
        observe()

    }

    private fun setupRecycler() {
        binding.rvAccounts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = accountsAdapter
        }
    }

    private fun onItemSelected(accountId: Int) {
        setFragmentResult(ACCOUNTS_FRAGMENT_RESULT, bundleOf(Pair(SELECTED_ACCOUNT_ID, accountId)))
        navController.navigateUp()

    }

    private fun observe() {
        observeState(viewModel.state) { state ->
            binding.accountLoader.isVisible = state.isLoading
            accountsAdapter.submitList(state.accounts)
        }

        observeEvent(viewModel.event) {
            when (it) {
                is AccountScreenEvent.Error -> binding.root.showSnackBar(it.message)
            }
        }
    }

    companion object {
        const val ACCOUNTS_FRAGMENT_RESULT = "accounts_fragment_result"
        const val SELECTED_ACCOUNT_ID = "selected_account_id"
    }

}