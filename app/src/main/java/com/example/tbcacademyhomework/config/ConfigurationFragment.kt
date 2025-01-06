package com.example.tbcacademyhomework.config

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.game.GameFragment
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentConfigurationBinding


class ConfigurationFragment : Fragment() {
    private val configItemAdapter by lazy {
        GameConfigAdapter()
    }

    private val configValues = mutableListOf(
        ConfigurationItem(value = 9, selected = true),
        ConfigurationItem(value = 16),
        ConfigurationItem(value = 25)
    )

    private var _binding: FragmentConfigurationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigurationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        initListeners()


    }

    private fun initListeners() {
        configItemAdapter.onClickListener {
            configValues.replaceAll { value -> value.copy(selected = it == value.id) }
            configItemAdapter.submitList(configValues.toList())
        }

        binding.btnConfirm.setOnClickListener {
            val selectedItemId = configValues.find { it.selected }
            selectedItemId?.let {
                parentFragmentManager.beginTransaction().replace(
                    R.id.container,
                    GameFragment.newInstance(it.value)
                ).addToBackStack(null).commit()
            }

        }
    }

    private fun setupRecycler() {
        binding.rvConfig.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvConfig.adapter = configItemAdapter
        configItemAdapter.submitList(configValues.toList())

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}