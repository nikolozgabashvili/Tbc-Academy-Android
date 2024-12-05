package com.example.tbcacademyhomework

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()
        initListeners()


    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            validateUser()
        }

        binding.btnAgain.setOnClickListener {
            clearScreen()
        }

        binding.btnClear.setOnLongClickListener {
            clearScreen()
            true
        }
        //zipping linearLayout's children so when text changes error will get cleared
        binding.etLinearContainer.children.toList().zipWithNext { editTxt, errorView ->
            //checking if view is Edittext and next one is TextView since after input there is one textview for displaying error in
            if (editTxt is EditText && errorView is TextView) {
                //add text change listener on editText and clearing its corresponding errorView on Change
                editTxt.doOnTextChanged { _, _, _, _ ->
                    errorView.clearText()
                    errorView.visibility = View.GONE
                }
            }
        }
    }

    private fun clearScreen() {
        user = null
        binding.etLinearContainer.children.forEach { child ->
            if (child is TextView) {
                child.clearText()
            }
        }
        resetToInitialScreen()
    }

    private fun resetToInitialScreen() {
        binding.btnClear.visibility = View.VISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.etLinearContainer.visibility = View.VISIBLE
        binding.tvLinearContainer.visibility = View.GONE
    }

    private fun validateUser() {
        user = User(
            name = binding.etFirstName.text.toString(),
            lastName = binding.etLastName.text.toString(),
            age = binding.etAge.text.toString(),
            username = binding.etUsername.text.toString(),
            email = binding.etEmail.text.toString()
        )
        user?.let { user ->
            binding.tvEmailError.setValidation(user.emailState, this)
            binding.tvAgeError.setValidation(user.ageState, this)
            binding.tvFirstNameError.setValidation(user.firstNameState, this)
            binding.tvUsernameError.setValidation(user.userNameState, this)
            binding.tvLastNameError.setValidation(user.lastNameState, this)


            if (user.isUserValid) {
                displayUserInfo()
            }
        }
    }

    private fun displayUserInfo() {
        user?.let { user ->
            binding.etLinearContainer.visibility = View.GONE
            binding.btnSave.visibility = View.GONE
            binding.btnClear.visibility = View.GONE

            binding.tvAge.text = getString(R.string.display_age, user.age)
            binding.tvFullName.text =
                getString(R.string.display_fullName, "${user.name} ${user.lastName}")
            binding.tvEmail.text = getString(R.string.display_email, user.email)
            binding.tvUsername.text = getString(R.string.display_userName, user.username)
            binding.tvLinearContainer.visibility = View.VISIBLE
        }

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