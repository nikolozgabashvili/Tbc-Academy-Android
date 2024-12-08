package com.example.tbcacademyhomework

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.ActivityMainBinding
import com.example.tbcacademyhomework.user.User
import com.example.tbcacademyhomework.user.UserDataValidator
import com.example.tbcacademyhomework.user.UserDatabase
import com.example.tbcacademyhomework.validators.ValidationType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userDatabase = UserDatabase()
    private val userDataValidator = UserDataValidator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()
        initListeners()


    }

    private fun initListeners() {

        binding.btnAddUser.setOnClickListener {
            addUser()
        }

        userDatabase.onUserCountChangedListener { amount ->
            binding.tvUserCount.text = getString(R.string.users_count, amount.toString())
        }

        binding.btnGetUser.setOnClickListener {
            getUser()
        }

        binding.btnGoBack.setOnClickListener {
            goBack()
        }

        binding.ContainerScreen1.children.toList().zipWithNext { input, errorTxt ->
            if (input is EditText && errorTxt is TextView) {
                input.doOnTextChanged { _, _, _, _ ->
                    errorTxt.visibility = View.GONE
                }
            }
        }


    }

    private fun goBack() {
        binding.ContainerScreen1.visibility = View.VISIBLE
        binding.ContainerScreen2.visibility = View.GONE
    }

    private fun getUser() {
        val searchEmail = binding.etSearch.text.toString()
        val emailValidation = userDataValidator.validate(searchEmail, ValidationType.Email)
        binding.tvSearchError.isVisible = emailValidation.isError
        if (emailValidation.isError) {
            emailValidation.errorMessage?.let { errorMsg ->
                binding.tvSearchError.text = getString(errorMsg)

            }
        } else {
            val user = userDatabase.getUser(searchEmail)
            binding.ContainerScreen1.visibility = View.GONE
            binding.ContainerScreen2.visibility = View.VISIBLE
            clearScreen1()
            user?.let { usr ->
                binding.tvUserFullName.text = getString(R.string.full_name_with_value, usr.fullName)
                binding.tvUserEmail.text = getString(R.string.email_with_value, usr.email)
            }

            if (user == null) {
                binding.tvUserEmail.text = getString(R.string.user_not_found)
                binding.tvUserFullName.text = ""
            }

        }
    }

    private fun clearScreen1() {
        binding.etSearch.text?.clear()
        binding.tvSearchError.text = ""
        binding.etEmail.text?.clear()
        binding.tvEmailError.text = ""
        binding.etFullName.text?.clear()
        binding.tvFullNameError.text = ""
    }


    private fun addUser() {
        val userEmail = binding.etEmail.text.toString()
        val userFullName = binding.etFullName.text.toString()

        val emailValidation =
            userDataValidator.validate(userEmail, ValidationType.Email)
        val nameValidation =
            userDataValidator.validate(userFullName, ValidationType.FullName)

        binding.tvFullNameError.isVisible = nameValidation.isError
        binding.tvEmailError.isVisible = emailValidation.isError

        if (!emailValidation.isError && !nameValidation.isError) {
            if (userDatabase.userAlreadyExists(userEmail)) {
                makeToast(getString(R.string.user_already_exists))
            } else {
                val user = User(
                    fullName = userFullName,
                    email = userEmail
                )

                userDatabase.addUser(user)
                makeToast(getString(R.string.user_added_successfully))

            }
        } else {
            nameValidation.errorMessage?.let { msg ->
                binding.tvFullNameError.text = getString(msg)
            }

            emailValidation.errorMessage?.let { msg ->
                binding.tvEmailError.text = getString(msg)
            }

        }
    }

    private fun makeToast(toastText: String) {
        Toast.makeText(
            this, toastText,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleEdgeToEdge() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding = if (imeInsets.bottom == 0) systemBars.bottom else imeInsets.bottom
            view.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = bottomPadding
            )
            insets
        }
    }
}