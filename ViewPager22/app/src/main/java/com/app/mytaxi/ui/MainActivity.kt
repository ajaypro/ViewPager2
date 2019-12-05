package com.app.mytaxi.ui

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.app.mytaxi.R
import com.app.mytaxi.di.component.ActivityComponent
import com.app.mytaxi.ui.base.BaseActivity
import com.app.mytaxi.utils.display.Display
import com.app.mytaxi.utils.network.NetworkHelper
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<SharedViewModel>() {

    lateinit var viewPager: ViewPager2

    lateinit var tabLayout: TabLayout

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun layoutId() = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpView(savedInstanceState: Bundle?) {

        setSupportActionBar(toolbar)

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = cabViewPager
        viewPager.apply {

            adapter = ViewPagerAdapter(this@MainActivity)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        tabLayout = tabs

        TabLayoutMediator(tabs, viewPager) { tabs, position ->
            tabs.text = when (position) {
                0 -> "Taxi"
                else -> "Pool"
            }

        }.attach()

        // Initializing the viewmodel in the activity

        if (networkHelper.isNetworkConnected()) {
            viewModel.onCreate()
        } else Display.snackShow(this, activity_main, getText(R.string.connection_error))
    }
}
