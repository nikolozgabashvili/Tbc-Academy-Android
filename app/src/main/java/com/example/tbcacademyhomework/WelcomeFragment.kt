package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tbcacademyhomework.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().finish()
            }

            btnApple.setOnClickListener {
                root.makeSnackBar(getString(R.string.apple_button_pressed))
            }

            btnGoogle.setOnClickListener {
                root.makeSnackBar(getString(R.string.google_button_pressed))
            }

            btnFacebook.setOnClickListener {
                root.makeSnackBar(getString(R.string.facebook_button_pressed))
            }

            btnSignUp.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SignUpFragment()).addToBackStack(null)
                    .commit()
            }
            btnSignIn.setOnClickListener {
                root.makeSnackBar(getString(R.string.sign_in_pressed))
            }

        }
    }

}