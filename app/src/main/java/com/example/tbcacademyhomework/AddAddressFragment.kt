package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.AddressListFragment.Companion.ADDRESS_ID_KEY
import com.example.tbcacademyhomework.adapters.LocationIconsHelper
import com.example.tbcacademyhomework.adapters.icon.LocationIconAdapter
import com.example.tbcacademyhomework.databinding.FragmentAddAddressBinding
import com.example.tbcacademyhomework.models.Address


class AddAddressFragment : Fragment() {

    private var _binding: FragmentAddAddressBinding? = null
    private val binding get() = _binding!!

    private val locationIconsAdapter by lazy { LocationIconAdapter() }
    private val locationIconsHelper by lazy { LocationIconsHelper() }

    private var selectedAddressId: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAddressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        selectedAddressId = arguments?.getInt(ADDRESS_ID_KEY).takeIf { it != 0 }
        selectedAddressId?.let {
            getCurrentAddressInfo(it)
        }
        initViews()
    }

    private fun getCurrentAddressInfo(id: Int) {
        val address = AddressDatabase.getAddressById(id)
        address?.let { adrs ->
            binding.etLocationName.setText(adrs.title)
            binding.etAddress.setText(adrs.address)
            locationIconsHelper.setActiveIconWithDrawable(adrs.image)
            binding.btnAdd.text = getString(R.string.edit)

        }

    }

    private fun initViews() {
        setupRecycler()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            addAddress()
        }

        locationIconsHelper.onDataChange {
            locationIconsAdapter.submitList(locationIconsHelper.getData())
        }

        locationIconsAdapter.onCLick { id ->
            locationIconsHelper.selectItem(id)
        }
    }

    private fun addAddress() {
        val locationName = binding.etLocationName.text.toString()
        val address = binding.etAddress.text.toString()
        val selectedIcon = locationIconsHelper.getSelectedIconDrawable()

        if (locationName.isEmpty()) binding.etLocationName.error =
            getString(R.string.empty_field_error)
        if (address.isEmpty()) binding.etAddress.error = getString(R.string.empty_field_error)
        if (address.isNotEmpty() && locationName.isNotEmpty()) {
            selectedAddressId?.let { addressId ->
                AddressDatabase.editAddress(
                    Address(
                        id = addressId,
                        address = address,
                        title = locationName,
                        image = selectedIcon

                    )
                )
            } ?: run{
                AddressDatabase.addAddress(
                    address = address,
                    selectedIcon = selectedIcon,
                    locationName = locationName
                )
            }
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecycler() {
        binding.rvLocationIcon.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvLocationIcon.adapter = locationIconsAdapter
        locationIconsAdapter.submitList(locationIconsHelper.getData())
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }


}