package com.example.tbcacademyhomework.domain.auth.screen.login

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentLoginBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.hide
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.core.validation.ValidationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private val validationAdapter by lazy { ValidationAdapter() }


    override fun init() {
        initViews()
        initListeners()
        initValidationRecycler()
        initObservers()

    }


    private fun initViews() {
        binding.topBar.btnStart.hide()
        binding.topBar.tvTitle.text = getString(R.string.login)
    }

    private fun initListeners() {
        binding.etEmail.setOnTextChangedListener(viewModel::onEmailChanged)
        binding.etPassword.setOnTextChangedListener(viewModel::onPasswordChanged)
        binding.btnLogin.setOnClickListener {
            viewModel.onLoginClicked()
        }
    }


    private fun initValidationRecycler() {
        binding.rvValidations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null
            adapter = validationAdapter
        }

    }

    private fun initObservers() {
        launchCoroutineScope {
            viewModel.state.collectLatest {

            }
        }

    }
}