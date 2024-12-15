package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.tbcacademyhomework.databinding.ActivityDiscoveryBinding
import com.google.firebase.auth.FirebaseAuth

class DiscoveryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiscoveryBinding

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoveryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()
        firebaseAuth = FirebaseAuth.getInstance()

        setupPage()
    }

    private fun setupPage() {
        val currentUser = firebaseAuth.currentUser
        binding.tvUserName.text = getString(R.string.display_username, currentUser?.displayName)
        binding.tvUserEmail.text = getString(R.string.display_email, currentUser?.email)
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