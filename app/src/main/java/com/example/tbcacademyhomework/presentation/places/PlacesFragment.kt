package com.example.tbcacademyhomework.presentation.places

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.tbcacademyhomework.databinding.FragmentPlacesBinding
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.domain.util.isError
import com.example.tbcacademyhomework.domain.util.isLoading
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class PlacesFragment : BaseFragment<FragmentPlacesBinding>(FragmentPlacesBinding::inflate) {

    private val placesViewModel: PlacesViewModel by viewModels()
    private val placesAdapter by lazy { PlacesAdapter() }

    override fun init(savedInstanceState: Bundle?) {
        initListeners()
        initViewPager()
        initObservers()


    }

    private fun initListeners() {
        binding.btnRetry.setOnClickListener {
            placesViewModel.fetchPlaces()
        }
    }

    private fun initViewPager() {


        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val scaleFactor = 1 - abs(position)
            page.scaleY = 0.85f + scaleFactor * 0.15f
        }
        binding.vpPlaces.apply {
            adapter = placesAdapter
            setPageTransformer(transformer)
            offscreenPageLimit = 1
            clipToPadding = false
            clipChildren = false
            setPadding(80, 0, 80, 0)
        }

    }

    private fun initObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                placesViewModel.state.collectLatest { resource ->
                    binding.loaderItem.root.isVisible = resource.isLoading()
                    binding.errorWrapper.isVisible = resource.isError()
                    binding.vpPlaces.isVisible = !resource.isLoading()
                    when (resource) {
                        is Resource.Success -> {
                            placesAdapter.submitList(resource.data)
                        }

                        else -> Unit
                    }
                }
            }
        }

    }

}