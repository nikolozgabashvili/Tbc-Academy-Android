package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.adapters.LocationIconsHelper
import com.example.tbcacademyhomework.adapters.address.AddressAdapter
import com.example.tbcacademyhomework.adapters.address.AddressAdapterCallback
import com.example.tbcacademyhomework.databinding.FragmentAddressListBinding

class AddressListFragment : Fragment() {
    private var _binding: FragmentAddressListBinding? = null
    private val binding get() = _binding!!

    private val addressAdapter by lazy { AddressAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupAddressRecycler()

    }

    private fun setupListeners() {
        AddressDatabase.onListChanged {
            addressAdapter.submitList(AddressDatabase.getAddresses())
        }
        binding.btnAddAddress.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddAddressFragment()).addToBackStack(null)
                .commit()
        }

    }

    private fun setupAddressRecycler() {
        binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAddress.adapter = addressAdapter
        addressAdapter.submitList(AddressDatabase.getAddresses())

        addressAdapter.onCallback { callback ->
            when (callback) {
                is AddressAdapterCallback.OnClick -> AddressDatabase.addressSelected(callback.id)
                is AddressAdapterCallback.OnEditClick -> editAddress(callback.id)
                is AddressAdapterCallback.OnLongClick -> AddressDatabase.removeAddress(callback.id)
            }
        }
    }

    private fun editAddress(id: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AddAddressFragment().apply {
                arguments = Bundle().apply {
                    putInt(ADDRESS_ID_KEY, id)
                }
            }
            ).addToBackStack(null).commit()
    }

    companion object {
        const val ADDRESS_ID_KEY = "address_id"
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}