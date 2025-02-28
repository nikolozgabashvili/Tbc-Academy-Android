package com.example.tbcacademyhomework.presentation.map

import android.content.Context
import com.example.tbcacademyhomework.presentation.map.model.Location
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class LocationRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<Location>
) : DefaultClusterRenderer<Location>(context, map, clusterManager) {



    override fun onBeforeClusterItemRendered(item: Location, markerOptions: MarkerOptions) {
        markerOptions.title(item.title).position(item.position)
    }

    override fun onClusterItemRendered(clusterItem: Location, marker: Marker) {
        marker.tag = clusterItem
    }
}