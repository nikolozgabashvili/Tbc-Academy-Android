package com.example.tbcacademyhomework.presentation.image_selector_screen

import android.net.Uri
import com.example.tbcacademyhomework.presentation.common.util.ByteArrayHolder

data class ImageSelectorUiState(
    val uploading: Boolean = false,
    val compressedByteArray: ByteArrayHolder? = null,
    val tempUri: Uri? = null,
)
