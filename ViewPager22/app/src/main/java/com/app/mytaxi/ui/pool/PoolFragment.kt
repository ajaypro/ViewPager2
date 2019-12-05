package com.app.mytaxi.ui.pool

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mytaxi.R
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.di.component.FragmentComponent
import com.app.mytaxi.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_pool.*
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 16-11-2019, 21:48
 */

class PoolFragment : BaseFragment() {

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var poolAdapter: PoolAdapter

    override fun layoutId() = R.layout.fragment_pool

    var poolDetails: List<Area> = ArrayList()

    companion object {

        const val TAG = "POOL_FRAGMENT"
        fun instance(): PoolFragment {

            val args = Bundle()
            val fragment = PoolFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun setUpView(view: View) {
        pool_recycler_view.apply {
            adapter = poolAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun setUpLiveDataObservers() {
        super.setUpLiveDataObservers()

        viewModel.loading.observe(this, Observer {
            poolProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.locations.observe(this, Observer {
            it.data?.run {
                poolDetails = poolAdapter.poolData(this)
                poolAdapter.appendData(poolDetails) }
        })
    }
}