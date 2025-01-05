package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.ActivityMainBinding
import com.example.tbcacademyhomework.messages.MessagesAdapter
import com.example.tbcacademyhomework.messages.MessagesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val messagesAdapter by lazy { MessagesAdapter() }
    private val messagesDatabase by lazy { MessagesDatabase() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMessagesRecycler()
        initListeners()


    }


    private fun setupMessagesRecycler() {
        binding.rvMessage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        binding.rvMessage.adapter = messagesAdapter

        messagesAdapter.onListUpdated {
            binding.rvMessage.scrollToPosition(0)
        }
    }

    private fun initListeners() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString()
            if (message.isNotBlank()) addMessage(message)

        }

    }

    private fun addMessage(message: String) {
        messagesDatabase.addMessage(message)
        binding.etMessage.text?.clear()
        messagesAdapter.submitList(messagesDatabase.getMessages())
    }


}