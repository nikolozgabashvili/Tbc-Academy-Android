package com.example.tbcacademyhomework.order

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.BottomSheetBinding
import com.example.tbcacademyhomework.order.models.Order
import com.example.tbcacademyhomework.order.models.OrderStatus
import com.example.tbcacademyhomework.order.models.Rating
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@Suppress("DEPRECATION")
class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!
    private var order: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        order = arguments?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(ORDER_KEY, Order::class.java)
            } else {
                arguments?.getSerializable(ORDER_KEY) as? Order
            }
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println(order)
        order?.let {
            setupBottomSheet(it)
        }
    }

    private fun setupBottomSheet(item: Order) {


        with(binding) {
            tvProductName.text = item.productName
            tvPrice.text = getString(
                R.string.price,
                item.price * item.quantity.toBigDecimal()
            )
            tvQuantity.text = getString(R.string.qty, item.quantity.toString())
            val itemColor = item.productColor.color
            vProductColor.backgroundTintList = ColorStateList.valueOf(resources.getColor(itemColor))
            val status = when (item.orderStatus) {
                OrderStatus.ACTIVE -> R.string.in_delivery
                OrderStatus.COMPLETED -> R.string.completed
            }
            tvColorName.text = getString(item.productColor.displayName)
            tvStatus.text = getString(status)
            ivProduct.setImageResource(item.productImg)

            btnCancel.setOnClickListener {
                dismiss()
            }

            btnSubmit.setOnClickListener {
                if (etReview.text.toString().isNotBlank()) {

                    parentFragmentManager.setFragmentResult(
                        REVIEW_KEY,
                        bundleOf(
                            REVIEW_BUNDLE_KEY to Rating(
                                orderId = item.id,
                                starRating = ratingBar.rating,
                                comment = etReview.text.toString()
                            )
                        )
                    )
                    dismiss()
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ORDER_KEY = "Order"
        const val REVIEW_KEY = "review"
        const val REVIEW_BUNDLE_KEY = "review_bundle"
    }
}