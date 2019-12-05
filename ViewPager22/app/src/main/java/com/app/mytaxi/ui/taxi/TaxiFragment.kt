package com.app.mytaxi.ui.taxi

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mytaxi.R
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.di.component.FragmentComponent
import com.app.mytaxi.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_taxi.*
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 16-11-2019, 21:42
 */

class TaxiFragment : BaseFragment() {

    @Inject
    lateinit var taxiAdapter: TaxiAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun layoutId() = R.layout.fragment_taxi

    var taxiDetails: List<Area> = ArrayList()

    companion object {

        const val TAG = "TAXI_FRAGMENT"
        fun instance(): TaxiFragment {

            val args = Bundle()
            val fragment = TaxiFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setUpLiveDataObservers() {
        super.setUpLiveDataObservers()

        viewModel.loading.observe(this, Observer {
            taxiProgressBar.visibility = if(it) View.VISIBLE else View.GONE
        })

        viewModel.locations.observe(this, Observer {
           it.data?.run {
               taxiDetails = taxiAdapter.taxiData(this)
               taxiAdapter.appendData(taxiDetails)}
        })
    }

    override fun setUpView(view: View) {
        taxi_recycler_view.apply {
            adapter = taxiAdapter
            layoutManager = linearLayoutManager
        }

    }



}