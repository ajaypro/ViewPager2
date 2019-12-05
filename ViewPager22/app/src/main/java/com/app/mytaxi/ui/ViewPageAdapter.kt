package com.app.mytaxi.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.mytaxi.ui.pool.PoolFragment
import com.app.mytaxi.ui.taxi.TaxiFragment

/**
 * Created by Ajay Deepak on 17-11-2019, 22:44
 */
class ViewPagerAdapter(activity: MainActivity)
    : FragmentStateAdapter(activity) {

    //check
    private val size = 2

    override fun getItemCount() = size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TaxiFragment.instance()
            }
            else -> {
                PoolFragment.instance()
            }
        }
    }
}