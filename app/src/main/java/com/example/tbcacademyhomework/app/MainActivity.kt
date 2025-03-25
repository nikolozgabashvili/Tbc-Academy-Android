package com.example.tbcacademyhomework.app

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (!isGranted) {
            showNotificationPermissionDeniedDialog()
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                }

                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }


    private fun showNotificationPermissionDeniedDialog() {

        val title = getString(R.string.notification_disabled)
        val message = getString(R.string.notification_disabled_message)
        val openSettings = getString(R.string.notification_disabled_open_settings)
        val cancel = getString(R.string.cancel)

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(openSettings) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts(PACKAGE, packageName, null)
                }
                startActivity(intent)
            }
            .setNegativeButton(cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        askNotificationPermission()
        handleEdgeToEdge()


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

    companion object {
        private const val PACKAGE = "package"
    }
}