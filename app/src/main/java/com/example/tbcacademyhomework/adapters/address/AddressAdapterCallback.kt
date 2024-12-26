package com.example.tbcacademyhomework.adapters.address

sealed interface AddressAdapterCallback {
    class OnClick(val id: Int) : AddressAdapterCallback
    class OnLongClick(val id: Int) : AddressAdapterCallback
    class OnEditClick(val id: Int) : AddressAdapterCallback
}