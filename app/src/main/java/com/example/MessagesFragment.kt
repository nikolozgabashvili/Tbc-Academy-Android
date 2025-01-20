package com.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.MessagesAdapter
import com.example.tbcacademyhomework.MessagesViewModel
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentMessagesBinding
import kotlinx.coroutines.launch

class MessagesFragment : BaseFragment<FragmentMessagesBinding>(FragmentMessagesBinding::inflate) {
    private val messagesAdapter by lazy { MessagesAdapter() }
    private val viewmodel :MessagesViewModel by viewModels()

    override fun init(savedInstanceState: Bundle?) {
        initViews()
        initRecycler()
        initObservers()

    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewmodel.messagesFlow.collect{
                    messagesAdapter.submitList(it)
                }
            }
        }
    }

    private fun initViews() {
        binding.btnSearch.setOnClickListener {
            search()
        }
    }

    private fun search() {
        val query = binding.etSearch.text.toString()
        viewmodel.search(query)

    }

    private fun initRecycler() {
        binding.rvMessages.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMessages.adapter = messagesAdapter

    }

}