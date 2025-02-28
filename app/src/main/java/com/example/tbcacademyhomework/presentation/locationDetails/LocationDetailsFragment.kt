package com.example.tbcacademyhomework.presentation.locationDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.tbcacademyhomework.databinding.FragmentLocationDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class LocationDetailsFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: LocationDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.tvTitle.text = args.title
        binding.tvAddress.text = args.addres
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
