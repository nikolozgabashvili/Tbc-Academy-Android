package com.example.tbcacademyhomework.presentation.map.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentMapBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.map.LocationRenderer
import com.example.tbcacademyhomework.presentation.map.MapScreenEvent
import com.example.tbcacademyhomework.presentation.map.model.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate) {

    private lateinit var permissionRationaleDialog: AlertDialog

    private var googleMap: GoogleMap? = null

    private val gpsDisabledDialog by lazy {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.gps_disabled))
            .setMessage(getString(R.string.gps_disabled_desc))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
    }


    private val mapViewModel: MapViewModel by viewModels()
    override fun init(savedInstanceState: Bundle?) {
        initMap()
        requestPermissions()
        initObservers()

    }

    override fun onResume() {
        super.onResume()
        mapViewModel.gpsStatusChanged(isGpsEnabled(requireContext()))
    }


    private fun initMap() {
        val map = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        map.getMapAsync { gMap ->
            googleMap = gMap
            googleMap?.uiSettings?.isZoomControlsEnabled = true

        }
    }

    private fun openBottomSheet(location: Location) {
        location.let {
            findNavController().navigate(
                MapFragmentDirections.actionMapFragmentToLocationDetailsFragment(
                    title = it.locationTitle,
                    addres = it.locationAddress
                )
            )
        }

    }


    @SuppressLint("MissingPermission")
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mapViewModel.state.collectLatest { state ->
                    binding.loading.isVisible = state.loading
                    if (state.locations.isNotEmpty()) {
                        googleMap?.let { map ->
                            addClusteredMarkers(map, state.locations)
                        }
                    }

                    if (state.hasLocationPermission && state.hasLocationEnabled) {
                        hideGpsDisabledDialog()
                        hidePermissionRationaleDialog()
                        googleMap?.isMyLocationEnabled = true
                        googleMap?.uiSettings?.isMyLocationButtonEnabled = true
                        getCurrentLocation()
                    } else if (!state.hasLocationEnabled) {
                        showGpsDisabledDialog()
                    }


                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mapViewModel.events.collect {
                    when (it) {
                        is MapScreenEvent.Error -> {
                            val error = it.error.getValue(requireContext())
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    private fun isGpsEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }


    private fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    mapViewModel.locationPermissionEnabled(true)
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    mapViewModel.locationPermissionEnabled(true)
                }

                else -> {
                    mapViewModel.locationPermissionEnabled(false)
                    showPermissionRationale(onPositiveButtonPressed = {
                        val intent =
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts(
                                    "package",
                                    requireContext().packageName,
                                    null
                                )
                            }
                        startActivity(intent)
                    }
                    )
                }
            }
        }

        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            showPermissionRationale {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }

        } else {

            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {

        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }


    private fun showPermissionRationale(onPositiveButtonPressed: () -> Unit) {
        permissionRationaleDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.location_permission_needed))
            .setMessage(getString(R.string.location_permission_needed_desc))
            .setPositiveButton(R.string.ok) { _, _ ->
                onPositiveButtonPressed()
            }
            .setCancelable(false)
            .create()
        permissionRationaleDialog.show()
    }

    private fun hidePermissionRationaleDialog() {
        if (::permissionRationaleDialog.isInitialized && permissionRationaleDialog.isShowing) {
            permissionRationaleDialog.dismiss()
        }
    }

    private fun showGpsDisabledDialog() {
        gpsDisabledDialog.show()
    }

    private fun hideGpsDisabledDialog() {
        gpsDisabledDialog.dismiss()
    }


    private fun addClusteredMarkers(googleMap: GoogleMap, locations: List<Location>) {
        val clusterManager = ClusterManager<Location>(requireContext(), googleMap)
        clusterManager.renderer = LocationRenderer(requireContext(), googleMap, clusterManager)


        clusterManager.addItems(locations)
        clusterManager.cluster()
        clusterManager.setOnClusterItemClickListener {
            openBottomSheet(it)
            true

        }


        googleMap.setOnCameraIdleListener {
            clusterManager.onCameraIdle()
        }
    }


}