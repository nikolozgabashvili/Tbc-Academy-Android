package com.example.tbcacademyhomework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (inflater: LayoutInflater, viewGroup: ViewGroup?, attachToRoot: Boolean) -> VB
) : Fragment() {
    private var _binding: VB? = null
    protected val binding = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = this.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        create(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle? = null)
    open fun create(savedInstanceState: Bundle? = null) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}