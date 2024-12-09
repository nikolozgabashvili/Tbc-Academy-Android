package com.example.tbcacademyhomework

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val anagramDatabase = AnagramDatabase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()
        initListeners()

    }

    private fun initListeners() {

        binding.btnSave.setOnClickListener {
            saveAnagram()
        }

        binding.etAnagramInput.doOnTextChanged { _, _, _, _ ->
            binding.tvAnagramInputError.visibility = View.GONE
        }

        binding.btnOutput.setOnClickListener {
            displayAnagrams()
        }
        binding.btnClear.setOnClickListener {
            clearScreen()
        }

    }

    private fun clearScreen() {
        binding.etAnagramInput.text?.clear()
        binding.tvAnagramInputError.visibility = View.GONE
        binding.tvAnagrams.text = null
        anagramDatabase.clearDatabase()

    }

    private fun displayAnagrams() {
        binding.tvAnagramInputError.visibility =
            View.GONE //setting visibility gone in case it was still visible until this point
        val anagrams = anagramDatabase.getAnagrams()
        binding.tvAnagrams.text = anagrams
    }

    private fun saveAnagram() {
        val anagram = binding.etAnagramInput.text.toString()

        binding.tvAnagramInputError.isVisible = anagram.isBlank()
        if (anagram.isBlank()) {
            binding.tvAnagramInputError.text = getString(R.string.field_must_not_be_empty)
        } else {

            if (anagramDatabase.anagramAlreadyAdded(anagram)) {
                makeToast(getString(R.string.anagram_already_exists))
            } else {
                anagramDatabase.addAnagram(anagram)
                binding.etAnagramInput.text?.clear()
                makeToast(getString(R.string.anagram_added_successfully))
            }

        }

    }

    private fun makeToast(toastMessage: String) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
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