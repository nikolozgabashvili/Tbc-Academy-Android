package com.example.tbcacademyhomework.order.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tbcacademyhomework.order.OrderListFragment
import com.example.tbcacademyhomework.order.models.OrderStatus

class OrderPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OrderListFragment.newInstance(OrderStatus.ACTIVE)
            1 -> OrderListFragment.newInstance(OrderStatus.COMPLETED)
            else -> OrderListFragment.newInstance(OrderStatus.ACTIVE)
        }
    }
}