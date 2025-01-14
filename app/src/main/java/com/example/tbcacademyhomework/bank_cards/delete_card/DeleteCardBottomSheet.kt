package com.example.tbcacademyhomework.bank_cards.delete_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbcacademyhomework.bank_cards.cards_pager_screen.CardsPagerFragment.Companion.REMOVE_CARD_REQUEST_KEY
import com.example.tbcacademyhomework.databinding.FragmentRemoveCardBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteCardBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentRemoveCardBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DeleteCardBottomSheetArgs>()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemoveCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        initListeners()
    }

    private fun initListeners() {
        binding.btnAccept.setOnClickListener {
            val resultBundle = Bundle().apply {
                putString(REMOVE_CARD_KEY, args.cardId)
            }
            setFragmentResult(REMOVE_CARD_REQUEST_KEY, resultBundle)
            dismiss()

        }

        binding.btnDismiss.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val REMOVE_CARD_KEY = "removed_card_key"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}