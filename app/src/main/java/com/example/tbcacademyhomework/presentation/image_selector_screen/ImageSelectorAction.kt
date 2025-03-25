package com.example.tbcacademyhomework.presentation.image_selector_screen

import android.net.Uri

sealed interface ImageSelectorAction {
    data object ProcessImage : ImageSelectorAction
    data class OnUriCreated(val uri: Uri):ImageSelectorAction
    data object UploadImage:ImageSelectorAction
}