package com.example.tbcacademyhomework.presentation.meal.home.screen.details

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentDetailsBinding
import com.example.tbcacademyhomework.presentation.core.base.BaseFragment
import com.example.tbcacademyhomework.presentation.core.util.factory.ViewModelFactoryProvider
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import com.example.tbcacademyhomework.presentation.core.util.loadImage
import com.example.tbcacademyhomework.presentation.core.util.openYouTubeVideo
import com.example.tbcacademyhomework.presentation.core.util.show
import com.example.tbcacademyhomework.presentation.core.util.visibleIf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val args: DetailsFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }

    @Inject
    lateinit var factory: DetailsViewModel.Factory

    private val viewModel: DetailsViewModel by viewModels {
        ViewModelFactoryProvider.provideViewModel {
            factory.create(args.meal.mealId)
        }
    }

    override fun init() {
        initViews()
        initListeners()
        initObservers()
    }


    private fun initViews() {
        binding.topBar.tvTitle.text = args.meal.mealName
        binding.topBar.btnEnd.setOnClickListener {
            viewModel.favouriteClick()
        }
        binding.topBar.btnEnd.show()


    }

    private fun initListeners() {
        binding.topBar.btnStart.setOnClickListener {
            navController.navigateUp()
        }
        binding.errorView.btnRetry.setOnClickListener {
            viewModel.getDetails()
        }
    }

    private fun initObservers() {
        launchCoroutineScope {
            viewModel.state.collectLatest {
                with(binding) {
                    val showContent = !it.loading && !it.isError
                    detailsLoader.root.visibleIf(it.loading)
                    containerDetails.visibleIf(showContent)
                    errorView.root.visibleIf(it.isError)
                    if (showContent) {
                        ivMeal.loadImage(it.details?.image)
                        tvDescription.text = it.details?.instructions
                        val ingredients = StringBuilder()
                        it.details?.ingredientMap?.forEach { (key, value) ->
                            if (!key.isNullOrEmpty() && !value.isNullOrEmpty())
                                ingredients.append("$key - $value").append("\n")

                        }
                        tvIngredients.text = ingredients.toString()
                        it.details?.youtubeVideo?.let {
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
                        if (it.isFavourite) {
                            binding.topBar.ivEndIcon.loadImage(R.drawable.ic_favourites)
                        } else {
                            binding.topBar.ivEndIcon.loadImage(R.drawable.ic_favourite_border)
                        }
                    }

                }

            }
        }
    }


}
