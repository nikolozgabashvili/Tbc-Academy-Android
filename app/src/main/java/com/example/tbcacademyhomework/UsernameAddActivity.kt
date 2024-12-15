package com.example.tbcacademyhomework

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.ActivityUsernameAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class UsernameAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsernameAddBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsernameAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()

        firebaseAuth = FirebaseAuth.getInstance()
        email = intent.extras?.getString(Consts.EMAIL_KEY, "") ?: ""
        password = intent.extras?.getString(Consts.PASSWORD_KEY, "") ?: ""

        setupTermsAndConditions()
        initListeners()


    }

    private fun initListeners() {
        binding.btnSignUp.setOnClickListener {
            signUpUser()
        }

        binding.containerBack.setOnClickListener {
            finish()
        }

        binding.etUsername.doOnTextChanged { _, _, _, _ ->
            binding.tvUserNameError.isVisible(false)

        }
    }

    private fun signUpUser() {
        with(binding) {
            val username = etUsername.text.toString()
            if (username.isNotBlank()) {
                tvUserNameError.isVisible(false)
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val updateRequest = userProfileChangeRequest {
                            displayName = username
                        }
                        it.user?.updateProfile(updateRequest)
                            ?.addOnSuccessListener {
                                val intent =
                                    Intent(this@UsernameAddActivity, DiscoveryActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)


                            }?.addOnFailureListener {
                                Toast.makeText(
                                    this@UsernameAddActivity,
                                    it.message,
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                    }.addOnFailureListener {
                        Toast.makeText(
                            this@UsernameAddActivity,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

            } else {
                tvUserNameError.isVisible(true)
                tvUserNameError.text = getString(R.string.empty_field_error)
            }
        }

    }

    private fun setupTermsAndConditions() {
        val termsAndPrivacyPolicy = getString(R.string.terms_and_privacy_policy)
        val termsOfService = getString(R.string.terms_of_service)
        val privacyPolicy = getString(R.string.privacy_policy)
        val spannable = SpannableStringBuilder(termsAndPrivacyPolicy)
        val termsStartIndex = termsAndPrivacyPolicy.indexOf(termsOfService)
        val termsEndIndex = termsStartIndex + termsOfService.length
        val privacyPolicyStartIndex = termsAndPrivacyPolicy.indexOf(privacyPolicy)
        val privacyPolicyEndIndex = privacyPolicyStartIndex + privacyPolicy.length


        val termsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(
                    this@UsernameAddActivity,
                    getString(R.string.terms_of_service_clicked),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = true
            }
        }


        val privacyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(
                    this@UsernameAddActivity,
                    getString(R.string.privacy_policy_click),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = true
            }

        }

        spannable.setSpan(
            termsClickableSpan,
            termsStartIndex,
            termsEndIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            privacyClickableSpan,
            privacyPolicyStartIndex,
            privacyPolicyEndIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tvTermsAndPrivacyPolicy.text = spannable
        binding.tvTermsAndPrivacyPolicy.movementMethod = LinkMovementMethod.getInstance()
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