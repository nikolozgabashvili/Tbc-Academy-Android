package com.example.tbcacademyhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()
        auth = FirebaseAuth.getInstance()
        initListeners()


    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            loginUser()
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

    private fun loginUser() {
        with(binding) {
            val emailText = etEmail.text.toString()
            val passwordText = etPassword.text.toString()
            if (emailText.isNotBlank() && isValidEmail(emailText) && passwordText.length >= 8) {
                tvPasswordError.isVisible(false)
                tvEmailError.isVisible(false)
                auth.signInWithEmailAndPassword(emailText, passwordText).addOnFailureListener {
                    Toast.makeText(
                        this@LoginActivity,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnSuccessListener {
                    val intent = Intent(this@LoginActivity, DiscoveryActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }


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