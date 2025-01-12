@file:Suppress("DEPRECATION")

package com.example.tbcacademyhomework.order

import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentOrderListBinding
import com.example.tbcacademyhomework.order.BottomSheetFragment.Companion.REVIEW_BUNDLE_KEY
import com.example.tbcacademyhomework.order.BottomSheetFragment.Companion.REVIEW_KEY
import com.example.tbcacademyhomework.order.adapter.OrderListAdapter
import com.example.tbcacademyhomework.order.models.Order
import com.example.tbcacademyhomework.order.models.OrderStatus
import com.example.tbcacademyhomework.order.models.Rating
import com.example.tbcacademyhomework.product.ProductColor
import java.math.BigDecimal
import java.util.UUID


class OrderListFragment :
    BaseFragment<FragmentOrderListBinding>(FragmentOrderListBinding::inflate) {
    private var selectedStatus: OrderStatus = OrderStatus.ACTIVE

    private val orderAdapter by lazy {
        OrderListAdapter(
            onclick = {

                onItemClicked(it)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val status = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(TAB_KEY, OrderStatus::class.java)
        } else {
            arguments?.getSerializable(TAB_KEY) as? OrderStatus
        }
        status?.let {
            selectedStatus = it
        }

    }


    override fun init(savedInstanceState: Bundle?) {
        setupRecycler()
        setupResultListener()
    }


    private fun onItemClicked(id: UUID) {
        val item = ordersData.find { it.id == id }
        println(item?.productName)
        item?.let {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.apply {
                arguments?.clear()
                arguments = Bundle().apply {
                    putSerializable(BottomSheetFragment.ORDER_KEY, item)
                }
            }
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }


    private fun setupRecycler() {

        binding.rvOrders.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
        orderAdapter.submitList(getOrders(selectedStatus))


    }

    private fun setupResultListener() {
        parentFragmentManager
            .setFragmentResultListener(REVIEW_KEY, viewLifecycleOwner) { _, bundle ->
                val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getSerializable(REVIEW_BUNDLE_KEY, Rating::class.java)
                } else {
                    arguments?.getSerializable(REVIEW_BUNDLE_KEY) as? Rating
                }
                result?.let {
                    updateOrderStatus(it)
                }

            }
    }

    private fun updateOrderStatus(rating: Rating) {
        ordersData.replaceAll { it.copy(rating = if (it.id == rating.orderId) rating else it.rating) }
        orderAdapter.submitList(getOrders(selectedStatus))

    }


    companion object {

        fun newInstance(tab: OrderStatus) = OrderListFragment().apply {
            arguments = Bundle().apply {
                putSerializable(TAB_KEY, tab)
            }
        }

        const val TAB_KEY = "tab_key"

        fun getOrders(status: OrderStatus) = ordersData.filter { it.orderStatus == status }.toList()

        private val ordersData = mutableListOf(
            Order(
                productName = "product1",
                productColor = ProductColor.BLACK,
                orderStatus = OrderStatus.ACTIVE,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(100L),
                productImg = R.drawable.img_product1
            ),
            Order(
                productName = "product2",
                productColor = ProductColor.BLUE_GRAY,
                orderStatus = OrderStatus.ACTIVE,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(120L),
                productImg = R.drawable.img_product2
            ),
            Order(
                productName = "product3",
                productColor = ProductColor.BROWN,
                orderStatus = OrderStatus.COMPLETED,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(130L),
                productImg = R.drawable.img_product4
            ),
            Order(
                productName = "product4",
                productColor = ProductColor.BLACK,
                orderStatus = OrderStatus.ACTIVE,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(100L),
                productImg = R.drawable.img_product3
            ),
            Order(
                productName = "product4",
                productColor = ProductColor.BLACK,
                orderStatus = OrderStatus.ACTIVE,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(180L),
                productImg = R.drawable.img_product2
            ),
            Order(
                productName = "product5",
                productColor = ProductColor.BLUE_GRAY,
                orderStatus = OrderStatus.ACTIVE,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(120L),
                productImg = R.drawable.img_product1
            ),
            Order(
                productName = "product6",
                productColor = ProductColor.BROWN,
                orderStatus = OrderStatus.ACTIVE,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(100L),
                productImg = R.drawable.img_product4
            ),
            Order(
                productName = "product7",
                productColor = ProductColor.BLACK,
                orderStatus = OrderStatus.COMPLETED,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(150L),
                productImg = R.drawable.img_product2
            ),
            Order(
                productName = "product8",
                productColor = ProductColor.BROWN,
                orderStatus = OrderStatus.COMPLETED,
                quantity = 1,
                rating = null,
                price = BigDecimal.valueOf(170L),
                productImg = R.drawable.img_product3
            ),
        )
    }

}