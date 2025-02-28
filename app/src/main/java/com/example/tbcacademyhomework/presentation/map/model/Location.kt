package com.example.tbcacademyhomework.presentation.map.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem


data class Location(
    val latLong: LatLong,
    val locationTitle: String,
    val locationAddress: String
) : ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(latLong.lat, latLong.long)
    }

    override fun getTitle(): String {
        return locationTitle
    }

    override fun getSnippet(): String {
        return locationAddress
    }

    override fun getZIndex(): Float? {
        return null
    }

}