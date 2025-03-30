package com.example.tbcacademyhomework.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<VB : ViewBinding>(
    private val inflate: (inflater: LayoutInflater, viewGroup: ViewGroup?, attachToRoot: Boolean) -> VB
) : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun init()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}