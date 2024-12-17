package com.example.tbcacademyhomework

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userDatabase = UsersDatabase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()

        initListeners()


    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            addUser()
        }

        binding.btnRemove.setOnClickListener {
            removeUser()
        }
        binding.btnUpdate.setOnClickListener {
            updateUser()
        }

        userDatabase.onDataChangedListener { userCount, deletedUsers ->
            binding.tvRegisteredUsers.text =
                getString(R.string.active_users_amount, userCount.toString())
            binding.tvRemovedUsers.text =
                getString(R.string.deleted_users_amount, deletedUsers.toString())
        }

        binding.etFirstName.doOnTextChanged { _, _, _, _ ->
            binding.tvFirstnameError.isVisible(false)
        }
        binding.etLastName.doOnTextChanged { _, _, _, _ ->
            binding.tvLastnameError.isVisible(false)
        }
        binding.etAge.doOnTextChanged { _, _, _, _ ->
            binding.tvAgeError.isVisible(false)
        }
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.tvEmailError.isVisible(false)
        }
    }

    private fun addUser() {

        val fieldValidationResponse = validateUserFields()
        if (fieldValidationResponse.isError()) return

        val user = User(
            firstName = binding.etFirstName.text.toString(),
            lastName = binding.etLastName.text.toString(),
            age = binding.etAge.text.toString().toInt(),
            email = binding.etEmail.text.toString()
        )
        val userAddResult = userDatabase.addUser(user)
        if (userAddResult.isSuccess()) {
            binding.tvStatus.setTextColor(Color.GREEN)
            binding.tvStatus.text = getString(R.string.success_add)
        } else {
            binding.tvStatus.setTextColor(Color.RED)
            userAddResult.message?.let {
                binding.tvStatus.text = getString(it)
            }
        }
    }

    private fun removeUser() {
        val email = binding.etEmail.text.toString()
        val emailValidationResult = validateName(email)
        binding.tvEmailError.setError(emailValidationResult, this)
        if (emailValidationResult.isError()) return
        val userRemoveResult = userDatabase.removeUser(email)
        if (userRemoveResult.isSuccess()) {
            binding.tvStatus.setTextColor(Color.GREEN)
            binding.tvStatus.text = getString(R.string.success_remove)
        } else {
            binding.tvStatus.setTextColor(Color.RED)
            userRemoveResult.message?.let {
                binding.tvStatus.text = getString(it)
            }

        }

    }

    private fun updateUser() {
        val fieldValidationResponse = validateUserFields()
        if (fieldValidationResponse.isError()) return

        val user = User(
            firstName = binding.etFirstName.text.toString(),
            lastName = binding.etLastName.text.toString(),
            age = binding.etAge.text.toString().toInt(),
            email = binding.etEmail.text.toString()
        )
        val userUpdateResult = userDatabase.updateUser(user)
        if (userUpdateResult.isSuccess()) {
            binding.tvStatus.setTextColor(Color.GREEN)
            binding.tvStatus.text = getString(R.string.success_update)
        } else {
            binding.tvStatus.setTextColor(Color.RED)
            userUpdateResult.message?.let {
                binding.tvStatus.text = getString(it)
            }
        }
    }


    private fun validateUserFields(): Response {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val ageString = binding.etAge.text.toString()

        val firstNameValidationResult = validateName(firstName)
        val lastNameValidationResult = validateName(lastName)
        val emailValidationResult = validateEmail(email)
        val ageValidationResult = validateAge(ageString)

        binding.tvAgeError.setError(ageValidationResult, this)
        binding.tvLastnameError.setError(lastNameValidationResult, this)
        binding.tvFirstnameError.setError(firstNameValidationResult, this)
        binding.tvEmailError.setError(emailValidationResult, this)
        return if (firstNameValidationResult.isSuccess() && lastNameValidationResult.isSuccess() && emailValidationResult.isSuccess() && ageValidationResult.isSuccess()) {
            Response.Success()
        } else {
            Response.Error(null)

        }
    }

    private fun handleEdgeToEdge() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding =
                if (imeInsets.bottom == 0) systemBars.bottom else imeInsets.bottom
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