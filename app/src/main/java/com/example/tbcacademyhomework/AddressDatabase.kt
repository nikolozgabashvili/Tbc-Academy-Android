package com.example.tbcacademyhomework

import androidx.annotation.DrawableRes
import com.example.tbcacademyhomework.models.Address


object AddressDatabase {

    private val addressList = mutableListOf<Address>()

    fun getAddresses(): List<Address> {
        return addressList.toList()
    }

    fun addressSelected(id: Int) {
        addressList.replaceAll { it.copy(isSelected = it.id == id) }
        listener()
    }

    private var listener: () -> Unit = {}

    fun onListChanged(listener: () -> Unit) {
        this.listener = listener
    }

    fun addAddress(
        address: String,
        @DrawableRes
        selectedIcon: Int,
        locationName: String
    ) {
        val firstItemId = if (addressList.isEmpty()) 1 else addressList.first().id + 1
        val newAddress = Address(
            id = firstItemId,
            image = selectedIcon,
            title = locationName,
            address = address
        )
        addressList.add(0, newAddress)
        listener()

    }

    fun removeAddress(id: Int) {
        addressList.removeIf { it.id == id }
        listener()
    }

    fun editAddress(address: Address) {
        addressList.replaceAll {
            if (it.id == address.id) it.copy(
                image = address.image,
                title = address.title,
                address = address.address
            ) else it
        }
        listener()
    }

    fun getAddressById(id: Int): Address? {
        return addressList.find { it.id == id }

    }

}