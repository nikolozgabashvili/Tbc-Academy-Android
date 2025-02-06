package com.example.tbcacademyhomework.presentation.passcode

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentPasscodeBinding
import com.example.tbcacademyhomework.presentation.passcode.indicator.PasscodeIndicatorAdapter
import com.example.tbcacademyhomework.presentation.utils.makeToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PasscodeFragment : BaseFragment<FragmentPasscodeBinding>(FragmentPasscodeBinding::inflate) {

    private val passcodeViewModel: PasscodeViewModel by viewModels()
    private val indicatorAdapter by lazy { PasscodeIndicatorAdapter() }
    private val passcodeAdapter by lazy {
        PasscodeButtonsAdapter(
            buttons = passcodeViewModel.getPasswordButtons(),
            onclickListener = passcodeViewModel::onButtonClicked
        )
    }


    override fun init(savedInstanceState: Bundle?) {
        initIndicatorRecycler()
        initPasswordButtonsRecycler()
        setupObservers()
    }


    private fun initIndicatorRecycler() {
        with(binding) {
            rvPasscodeIndicator.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvPasscodeIndicator.adapter = indicatorAdapter
        }
    }

    private fun initPasswordButtonsRecycler() {
        with(binding) {
            rvPassButtons.layoutManager = GridLayoutManager(requireContext(), 3)
            rvPassButtons.adapter = passcodeAdapter
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                passcodeViewModel.state.collectLatest { state ->
                    indicatorAdapter.submitList(state.indicators)

                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                passcodeViewModel.event.collect { event ->
                    when (event) {
                        PasscodeScreenEvent.Error -> {
                            makeToast(requireContext(), getString(R.string.error_wrong_password))
                        }

                        PasscodeScreenEvent.FingerPrint -> {
                            makeToast(requireContext(), getString(R.string.fingerprint_clicked))
                        }
                        PasscodeScreenEvent.Success -> {
                            makeToast(requireContext(), getString(R.string.success))
                        }
                    }

                }
            }
        }

    }


}