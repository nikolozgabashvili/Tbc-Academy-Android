@file:Suppress("DEPRECATION")

package com.example.tbcacademyhomework

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.databinding.FragmentOrderListBinding
import com.example.tbcacademyhomework.order.OrderAdapter
import com.example.tbcacademyhomework.order.OrderDatabase
import com.example.tbcacademyhomework.order.OrderStatus
import java.util.UUID


class OrderListFragment : Fragment() {
    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!

    private val orderDatabase by lazy { OrderDatabase() }
    private val orderAdapter by lazy { OrderAdapter() }
    private var activeFilter: OrderStatus = OrderStatus.PENDING


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentOrderListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilterButtons()
        initRecycler()
        setupResultListener()


    }

    private fun setupResultListener() {
        parentFragmentManager.setFragmentResultListener(
            OrderDetailsFragment.RESULT_KEY,
            this
        ) { _, bundle ->
            val (orderId, orderStatus) = bundle.run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getSerializable(
                        OrderDetailsFragment.ARG_ORDER_ID,
                        UUID::class.java
                    ) to getSerializable(OrderDetailsFragment.ARG_STATUS, OrderStatus::class.java)
                } else {
                    getSerializable(OrderDetailsFragment.ARG_ORDER_ID) as UUID to getSerializable(
                        OrderDetailsFragment.ARG_STATUS
                    ) as OrderStatus
                }
            }
            if (orderId != null && orderStatus != null) {
                orderDatabase.updateOrderStatus(orderId, orderStatus)
                updateUi()
            }

        }
    }

    private fun initFilterButtons() {
        with(binding) {

            btnPending.setOnClickListener {
                if (activeFilter != OrderStatus.PENDING) {
                    activeFilter = OrderStatus.PENDING
                    updateUi()
                }
            }

            btnDelivered.setOnClickListener {
                if (activeFilter != OrderStatus.DELIVERED) {
                    activeFilter = OrderStatus.DELIVERED
                    updateUi()
                }
            }

            btnCancelled.setOnClickListener {
                if (activeFilter != OrderStatus.CANCELLED) {
                    activeFilter = OrderStatus.CANCELLED
                    updateUi()
                }
            }
        }
    }

    private fun updateUi() {
        with(binding) {
            btnPending.isSelected(activeFilter == OrderStatus.PENDING)
            btnCancelled.isSelected(activeFilter == OrderStatus.CANCELLED)
            btnDelivered.isSelected(activeFilter == OrderStatus.DELIVERED)
            orderAdapter.submitList(orderDatabase.getOrdersByStatus(activeFilter))
        }

    }

    private fun initRecycler() {
        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrders.adapter = orderAdapter
        updateUi()

        orderAdapter.onDetailsClick { orderId ->
            navigateToDetails(orderId)

        }
    }

    private fun navigateToDetails(orderId: UUID) {
        val selectedOrder = orderDatabase.getById(orderId)
        selectedOrder?.let { order ->
            val orderNumber = order.orderNumber
            val trackingNumber = order.trackingNumber
            val orderStatus = order.status

            parentFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer, OrderDetailsFragment.newInstance(
                    orderId,
                    orderNumber,
                    trackingNumber,
                    orderStatus
                )
            ).addToBackStack(null).commit()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}