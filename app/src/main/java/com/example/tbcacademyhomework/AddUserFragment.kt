package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.SearchUserFragment.Companion.BUNDLE_KEY
import com.example.tbcacademyhomework.SearchUserFragment.Companion.DESC_KEY
import com.example.tbcacademyhomework.databinding.FragmentAddUserBinding
import kotlin.math.absoluteValue
import kotlin.random.Random


class AddUserFragment : Fragment() {
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

    }

    private fun initListeners() {
        with(binding) {

            btnAdd.setOnClickListener {
                validateUser()
            }

            etFirstName.doOnTextChanged { _, _, _, _ ->
                tvFirstNameError.isVisible(false)
            }
            etLastName.doOnTextChanged { _, _, _, _ ->
                tvLastNameError.isVisible(false)
            }
            etEmail.doOnTextChanged { _, _, _, _ ->
                tvEmailError.isVisible(false)
            }

            etAddress.doOnTextChanged { _, _, _, _ ->
                tvAddressError.isVisible(false)
            }
            etBirthday.doOnTextChanged { _, _, _, _ ->
                tvBirthdayError.isVisible(false)
            }
            etDescription.doOnTextChanged { _, _, _, _ ->
                tvDescError.isVisible(false)
            }
        }

    }

    private fun validateUser() {

        with(binding) {
            val email = etEmail.text.toString()
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val address = etAddress.text.toString()
            val birthday = etBirthday.text.toString()
            val desc = etDescription.text.toString()

            val emailValidationResult = validateEmail(email)
            val firstNameValidation = validateName(firstName)
            val lastNameNameValidation = validateName(lastName)
            val birthdayValidationResult = validateField(birthday)
            val addressValidationResult = validateField(address)
            val descriptionValidationResult = validateField(desc)

            tvEmailError.setError(emailValidationResult, requireContext())
            tvFirstNameError.setError(firstNameValidation, requireContext())
            tvLastNameError.setError(lastNameNameValidation, requireContext())
            tvBirthdayError.setError(birthdayValidationResult, requireContext())
            tvAddressError.setError(addressValidationResult, requireContext())
            tvDescError.setError(descriptionValidationResult, requireContext())

            if (emailValidationResult.isSuccess()
                && firstNameValidation.isSuccess()
                && lastNameNameValidation.isSuccess()
                && birthdayValidationResult.isSuccess()
                && addressValidationResult.isSuccess()
                && descriptionValidationResult.isSuccess()
            ) {
                val userAddResult = UserDatabase.addUser(
                    User(
                        id = Random.nextInt().absoluteValue,
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        birthday = birthday,
                        address = address,
                        desc = desc
                    )
                )

                if (userAddResult.isSuccess()) {
                    val bundle = Bundle().apply {
                        putString(DESC_KEY, desc)
                    }
                    parentFragmentManager.setFragmentResult(BUNDLE_KEY, bundle)
                    parentFragmentManager.popBackStack()
                } else {
                    userAddResult.message?.let {
                        root.makeSnackBar(getString(it))
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}