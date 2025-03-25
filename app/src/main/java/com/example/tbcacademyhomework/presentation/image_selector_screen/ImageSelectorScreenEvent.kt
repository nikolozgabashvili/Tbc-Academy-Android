package com.example.tbcacademyhomework.presentation.image_selector_screen

sealed interface ImageSelectorScreenEvent {
    data object Success:ImageSelectorScreenEvent
    data class Error(val error:String?):ImageSelectorScreenEvent
    data object NoImageError:ImageSelectorScreenEvent
}