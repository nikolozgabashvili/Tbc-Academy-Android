package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.tbcacademyhomework.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        setupInputs()
    }

    private fun initListeners() {
        with(binding) {

            btnBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            btnSignIn.setOnClickListener {
                root.makeSnackBar(getString(R.string.sign_in_pressed))
            }

            btnSignUp.setOnClickListener {
                root.makeSnackBar(getString(R.string.sign_up))
            }

            btnFb.setOnClickListener {
                root.makeSnackBar(getString(R.string.facebook_button_pressed))
            }

            btnGoogle.setOnClickListener {
                root.makeSnackBar(getString(R.string.google_button_pressed))
            }

            btnApple.setOnClickListener {
                root.makeSnackBar(getString(R.string.apple_button_pressed))
            }
        }

    }

    private fun setupInputs() {
        binding.etEmail.setOnFocusChangeListener { v, hasFocus ->
            v.background = if (hasFocus) {

                ContextCompat.getDrawable(requireContext(), R.drawable.bg_input_outlined)
            } else {
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_input)
            }
        }

        binding.etPassword.setOnFocusChangeListener { v, hasFocus ->
            v.background = if (hasFocus) {

                ContextCompat.getDrawable(requireContext(), R.drawable.bg_input_outlined)
            } else {
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_input)
            }
        }
        val activeColor = ContextCompat.getColor(requireContext(), R.color.black)
        val inactiveColor = ContextCompat.getColor(requireContext(), R.color.text_gray)

        binding.etEmail.setupColors(activeColor, inactiveColor)
        binding.etPassword.setupColors(activeColor, inactiveColor)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}