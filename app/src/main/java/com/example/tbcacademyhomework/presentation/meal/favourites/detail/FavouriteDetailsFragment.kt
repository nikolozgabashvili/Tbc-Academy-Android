package com.example.tbcacademyhomework.presentation.meal.favourites.detail

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentFavouriteDetailsBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.factory.ViewModelFactoryProvider
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.core.util.loadImage
import com.example.tbcacademyhomework.presentation.core.util.openYouTubeVideo
import com.example.tbcacademyhomework.presentation.core.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteDetailsFragment :
    BaseFragment<FragmentFavouriteDetailsBinding>(FragmentFavouriteDetailsBinding::inflate) {

    private val args: FavouriteDetailsFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }

    @Inject
    lateinit var factory: FavouriteDetailViewModel.Factory

    private val viewModel: FavouriteDetailViewModel by viewModels {
        ViewModelFactoryProvider.provideViewModel {
            factory.create(args.meal.mealId)
        }
    }

    override fun init() {
        initViews()
        initObservers()


    }

    private fun initViews() {
        binding.topBar.tvTitle.text = args.meal.mealName
        binding.topBar.btnStart.setOnClickListener {
            navController.navigateUp()
        }
        binding.topBar.btnEnd.show()
        binding.topBar.btnEnd.setOnClickListener {
            viewModel.onFavouriteClick()
        }
    }

    private fun initObservers() {
        launchCoroutineScope {
            viewModel.state.collectLatest { state ->
                with(binding) {

                    if (state.isFavourite) {
                        binding.topBar.ivEndIcon.loadImage(R.drawable.ic_favourites)
                    } else {
                        binding.topBar.ivEndIcon.loadImage(R.drawable.ic_favourite_border)
                    }
                    ivMeal.loadImage(
                        url = state.details?.image,
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    )

                    tvDescription.text = state.details?.instructions
                    val ingredients = StringBuilder()
                    state.details?.ingredientMap?.forEach { (key, value) ->
                        if (!key.isNullOrEmpty() && !value.isNullOrEmpty())
                            ingredients.append("$key - $value").append("\n")

                    }
                    tvIngredients.text = ingredients.toString()
                    state.details?.youtubeVideo?.let {
                        val spannableText = SpannableString(getString(R.string.see_video))
                        spannableText.setSpan(object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                requireContext().openYouTubeVideo(it)
                            }
                        }, 0, spannableText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        spannableText.setSpan(
                            ForegroundColorSpan(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.textPrimary
                                )
                            ),
                            0, spannableText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        tvSeeVideo.text = spannableText
                        tvSeeVideo.movementMethod =
                            android.text.method.LinkMovementMethod.getInstance()

                    }
                }

            }
        }
    }

}