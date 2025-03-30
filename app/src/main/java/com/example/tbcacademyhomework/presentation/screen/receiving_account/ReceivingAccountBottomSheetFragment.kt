package com.example.tbcacademyhomework.presentation.screen.receiving_account

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.databinding.FragmentReceivingAccountBottomSheetBinding
import com.example.tbcacademyhomework.presentation.base.BaseBottomSheetFragment
import com.example.tbcacademyhomework.presentation.common.extension.observeEvent
import com.example.tbcacademyhomework.presentation.common.extension.observeState
import com.example.tbcacademyhomework.presentation.common.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceivingAccountBottomSheetFragment :
    BaseBottomSheetFragment<FragmentReceivingAccountBottomSheetBinding>(
        FragmentReceivingAccountBottomSheetBinding::inflate
    ) {

    private val viewModel: ReceivingAccountViewModel by viewModels()
    private val navController by lazy { findNavController() }


    override fun init() {

        observe()
        listeners()

    }

    private fun listeners() {
        binding.btnConfirm.setOnClickListener {
            val input = binding.etAccountInfo.text.toString()
            viewModel.onAction(ReceivingAccountScreenAction.OnConfirm(input))
        }
    }

    private fun observe() {

        observeEvent(viewModel.event) { event ->
            when (event) {
                is ReceivingAccountScreenEvent.Error -> binding.root.showSnackBar(event.error)
                is ReceivingAccountScreenEvent.Success -> {
                    setFragmentResult(event.id)
                }
            }
        }
        observeState(viewModel.state) { state ->
            handleLoading(state.loading)


        }

    }

    private fun handleLoading(loading: Boolean) {
        with(binding) {
            progressLoader.isVisible = loading
            etLayoutSell.isEnabled = !loading
            btnConfirm.isEnabled = !loading
        }
    }

    private fun setFragmentResult(id: Int) {
        setFragmentResult(
            RECEIVING_ACCOUNT_FRAGMENT_RESULT,
            bundleOf(Pair(RECEIVING_ACCOUNT_ID, id))
        )
        navController.navigateUp()
    }

    companion object {
        const val RECEIVING_ACCOUNT_FRAGMENT_RESULT = "receiving_account_fragment_result"
        const val RECEIVING_ACCOUNT_ID = "receiving_account_id"
    }

}