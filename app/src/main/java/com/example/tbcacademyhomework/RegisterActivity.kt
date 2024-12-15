package com.example.tbcacademyhomework

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()
        initListeners()

    }

    private fun initListeners() {
        binding.btnNext.setOnClickListener {
            registerUser()
        }

        binding.containerBack.setOnClickListener {
            finish()
        }

        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            binding.tvEmailError.isVisible(false)

        }

        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            binding.tvPasswordError.isVisible(false)

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

    private fun registerUser() {
        with(binding) {
            val emailText = etEmail.text.toString()
            val passwordText = etPassword.text.toString()
            if (emailText.isNotBlank() && isValidEmail(emailText) && passwordText.length >= 8) {
                tvPasswordError.isVisible(false)
                tvEmailError.isVisible(false)

                val intent = Intent(this@RegisterActivity, UsernameAddActivity::class.java)
                intent.putExtra(Consts.EMAIL_KEY, emailText)
                intent.putExtra(Consts.PASSWORD_KEY, passwordText)
                startActivity(intent)


            }
            if (emailText.isEmpty()) {
                tvEmailError.isVisible(true)
                tvEmailError.text = getString(R.string.empty_field_error)
            } else if (!isValidEmail(emailText)) {
                tvEmailError.isVisible(true)
                tvEmailError.text = getString(R.string.invalid_email_error)
            }
            if (passwordText.isBlank()) {
                tvPasswordError.isVisible(true)
                tvPasswordError.text = getString(R.string.empty_field_error)
            } else if (passwordText.length < 8) {
                tvPasswordError.isVisible(true)
                tvPasswordError.text = getString(R.string.password_field_length_error)

            }

        }
    }
}