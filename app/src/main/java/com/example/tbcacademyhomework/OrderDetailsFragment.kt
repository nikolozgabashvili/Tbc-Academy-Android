package com.example.tbcacademyhomework

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.tbcacademyhomework.databinding.FragmentOrderDetailsBinding
import com.example.tbcacademyhomework.order.Order
import com.example.tbcacademyhomework.order.OrderStatus
import java.util.UUID


@Suppress("DEPRECATION")
class OrderDetailsFragment : Fragment() {

    private var _binding: FragmentOrderDetailsBinding? = null
    private val binding get() = _binding!!

    private var orderId: UUID? = null
    private var orderNumber: Int? = null
    private var trackingNumber: String? = null
    private var status: OrderStatus? = null

    private var canEdit: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            orderId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) it.getSerializable(
                ARG_ORDER_ID, UUID::class.java
            ) else it.getSerializable(ARG_ORDER_ID) as UUID

            status = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) it.getSerializable(
                ARG_STATUS, OrderStatus::class.java
            ) else it.getSerializable(ARG_STATUS) as OrderStatus

            orderNumber = it.getInt(ARG_ORDER_NUMBER)
            trackingNumber = it.getString(ARG_TRACKING_NUMBER)
            canEdit = status == OrderStatus.PENDING

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        with(binding) {
            orderNumber?.let {
                tvOrderNumber.text = requireContext().getString(R.string.order_name, it.toString())
            }
            trackingNumber?.let {
                tvTrackingNumber.text =
                    requireContext().getString(R.string.tracking_number_value, it)
            }
            status?.let {
                tvStatus.text = getString(R.string.status, it.name)
            }

            setupButtons()

        }
    }

    private fun setupButtons() {
        with(binding) {
            btnBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            btnCancel.isVisible = canEdit
            btnDeliver.isVisible = canEdit

            val bundle = Bundle().apply {
                putSerializable(ARG_ORDER_ID, orderId)
            }

            btnCancel.setOnClickListener {
                bundle.putSerializable(ARG_STATUS, OrderStatus.CANCELLED)
                parentFragmentManager.setFragmentResult(RESULT_KEY, bundle)
                parentFragmentManager.popBackStack()

            }

            btnDeliver.setOnClickListener {
                bundle.putSerializable(ARG_STATUS, OrderStatus.DELIVERED)
                parentFragmentManager.setFragmentResult(RESULT_KEY, bundle)
                parentFragmentManager.popBackStack()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val RESULT_KEY = "result_key"
        const val ARG_ORDER_ID = "order_id"
        private const val ARG_ORDER_NUMBER = "order_number"
        private const val ARG_TRACKING_NUMBER = "tracking_number"
        const val ARG_STATUS = "status"

        fun newInstance(
            orderId: UUID,
            orderNumber: Int,
            trackingNumber: String,
            status: OrderStatus
        ) =
            OrderDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ORDER_ID, orderId)
                    putInt(ARG_ORDER_NUMBER, orderNumber)
                    putString(ARG_TRACKING_NUMBER, trackingNumber)
                    putSerializable(ARG_STATUS, status)
                }
            }
    }
}