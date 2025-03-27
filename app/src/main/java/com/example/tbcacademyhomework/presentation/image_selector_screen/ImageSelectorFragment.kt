package com.example.tbcacademyhomework.presentation.image_selector_screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.FragmentImageSelectorBinding
import com.example.tbcacademyhomework.presentation.base.BaseFragment
import com.example.tbcacademyhomework.presentation.common.extension.loadImage
import com.example.tbcacademyhomework.presentation.common.extension.showSnackBar
import com.example.tbcacademyhomework.presentation.image_action_screen.ImageAction
import com.example.tbcacademyhomework.presentation.image_action_screen.ImageActionBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class ImageSelectorFragment :
    BaseFragment<FragmentImageSelectorBinding>(FragmentImageSelectorBinding::inflate) {

    private val navController by lazy { findNavController() }
    private val viewModel: ImageSelectorViewModel by viewModels()

    private val imageSelectListener =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    viewModel.onAction(ImageSelectorAction.OnUriCreated(it))
                    viewModel.onAction(ImageSelectorAction.ProcessImage)
                }

            }
        }

    private val cameraResultListener =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.onAction(ImageSelectorAction.ProcessImage)
            }
        }


    override fun init(savedInstanceState: Bundle?) {

        setupListeners()
        setupResultListener()
        setupObservers()

    }


    private fun setupListeners() {
        binding.btnSelectImage.setOnClickListener {
            navController.navigate(ImageSelectorFragmentDirections.actionImageSelectorFragmentToImageActionBottomSheetFragment())
        }

        binding.btnUploadImg.setOnClickListener {
            viewModel.onAction(ImageSelectorAction.UploadImage)
        }
    }

    private fun setupResultListener() {
        setFragmentResultListener(ImageActionBottomSheetFragment.FRAGMENT_REQUEST_KEY) { _, bundle ->
            val itemName = bundle.getString(ImageActionBottomSheetFragment.SELECTED_ITEM_KEY)
            itemName?.let {
                val item = ImageAction.valueOf(itemName)
                handleItemAction(item)
            }

        }
    }

    private fun handleItemAction(item: ImageAction) {
        when (item) {
            ImageAction.SELECT_FROM_LOCALE -> {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                imageSelectListener.launch(intent)
            }

            ImageAction.TAKE_PHOTO -> {
                openCamera()
            }
        }

    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {

                    handleLoading(it.uploading)
                    it.compressedByteArray?.let {
                        binding.ivSelectedImage.loadImage(it.byteArray)
                    }
                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is ImageSelectorScreenEvent.Error -> {
                            binding.root.showSnackBar(event.error)
                        }

                        ImageSelectorScreenEvent.Success -> {
                            val message = getString(R.string.uploaded_success)
                            binding.root.showSnackBar(message)
                        }

                        ImageSelectorScreenEvent.NoImageError -> {
                            val message = getString(R.string.image_not_selected)
                            binding.root.showSnackBar(message)
                        }
                    }
                }
            }
        }
    }

    private fun handleLoading(uploading: Boolean) {
        binding.btnSelectImage.isEnabled = !uploading
        binding.btnUploadImg.isEnabled = !uploading
        binding.itemLoader.root.isVisible = uploading
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    private fun openCamera() {
        val photoFile = createImageFile()
        val photoUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            photoFile
        )

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        }
        viewModel.onAction(ImageSelectorAction.OnUriCreated(photoUri))
        cameraResultListener.launch(cameraIntent)
    }

}
