package com.example.tbcacademyhomework.presentation.image_selector_screen

import android.graphics.Bitmap
import android.net.Uri

data class ImageSelectorUiState(
    val uploading: Boolean = false,
    val compressedImage: Bitmap? = null,
    val tempUri: Uri? = null
)
