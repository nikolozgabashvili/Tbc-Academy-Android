package com.example.tbcacademyhomework

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
            btnPending.active(activeFilter == OrderStatus.PENDING)
            btnCancelled.active(activeFilter == OrderStatus.CANCELLED)
            btnDelivered.active(activeFilter == OrderStatus.DELIVERED)
            orderAdapter.submitList(orderDatabase.getOrdersByStatus(activeFilter))
        }

    }

    private fun initRecycler() {
        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrders.adapter = orderAdapter
        updateUi()

        orderAdapter.onDetailsClick {
            println(it)
        }
    }


}