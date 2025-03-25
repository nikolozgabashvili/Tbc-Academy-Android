package com.example.tbcacademyhomework.presentation.image_action_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.FragmentImageActionBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImageActionBottomSheetFragment :
    BottomSheetDialogFragment() {
    private val navController by lazy { findNavController() }
    private var _binding: FragmentImageActionBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val itemAdapter by lazy {
        ImageActionAdapter(
            onClick = {
                itemSelected(it)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageActionBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
    }


    private fun initRecycler() {
        binding.rvImageActions.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = itemAdapter
        }
    }

    private fun itemSelected(item: String) {
        val data = Bundle().apply {
            putString(SELECTED_ITEM_KEY, item)
        }
        setFragmentResult(FRAGMENT_REQUEST_KEY, data)
        navController.navigateUp()

    }

    companion object {
        const val FRAGMENT_REQUEST_KEY = "request_key"
        const val SELECTED_ITEM_KEY = "selected_item_key"
    }
}