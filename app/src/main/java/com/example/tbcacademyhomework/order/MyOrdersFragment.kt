package com.example.tbcacademyhomework.order

import android.os.Bundle
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.base.BaseFragment
import com.example.tbcacademyhomework.databinding.FragmentMyOrdersBinding
import com.example.tbcacademyhomework.order.adapter.OrderPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MyOrdersFragment : BaseFragment<FragmentMyOrdersBinding>(
    FragmentMyOrdersBinding::inflate
) {

    override fun init(savedInstanceState: Bundle?) {
        initViewPager()

    }

    private fun initViewPager() {
        binding.vpOrders.adapter = OrderPagerAdapter(requireActivity())

        TabLayoutMediator(
            binding.tabs, binding.vpOrders
        ) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.active)
                1 -> getString(R.string.completed)
                else -> ""
            }

        }.attach()
    }


}