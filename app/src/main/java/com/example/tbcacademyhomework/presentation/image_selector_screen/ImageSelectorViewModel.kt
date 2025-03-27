package com.example.tbcacademyhomework.presentation.image_selector_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcacademyhomework.domain.usecase.CompressImageUseCase
import com.example.tbcacademyhomework.domain.usecase.UploadImageUseCase
import com.example.tbcacademyhomework.domain.util.Resource
import com.example.tbcacademyhomework.presentation.common.util.ByteArrayHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSelectorViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val compressImageUseCase: CompressImageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ImageSelectorUiState())
    val state = _state.asStateFlow()
    val a = "".toByteArray()
    private val eventChannel = Channel<ImageSelectorScreenEvent>()
    val event = eventChannel.receiveAsFlow()

    fun onAction(action: ImageSelectorAction) {
        when (action) {
            is ImageSelectorAction.ProcessImage -> {
                compressImage()

            }

            is ImageSelectorAction.OnUriCreated -> {
                _state.update { it.copy(tempUri = action.uri) }

            }

            ImageSelectorAction.UploadImage -> {
                uploadImage()
            }
        }
    }

    private fun uploadImage() {
        viewModelScope.launch {
            val byteArr = state.value.compressedByteArray?.byteArray
            byteArr?.let {
                uploadImageUseCase(it).collect { resource ->
                    _state.update { it.copy(uploading = resource is Resource.Loading) }

                    if (resource is Resource.Success) {
                        eventChannel.send(ImageSelectorScreenEvent.Success)
                    }

                    if (resource is Resource.Error) {
                        eventChannel.send(ImageSelectorScreenEvent.Error(resource.error))
                    }

                }
            } ?: run {
                eventChannel.send(ImageSelectorScreenEvent.NoImageError)
            }
        }
    }

    private fun compressImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val uriString = state.value.tempUri.toString()
            compressImageUseCase(uriString,80)?.let { btArray ->
                _state.update { it.copy(compressedByteArray = ByteArrayHolder(btArray)) }

            }

        }
    }


}