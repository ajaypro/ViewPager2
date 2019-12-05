package com.app.mytaxi.ui.taxi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.mytaxi.R
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.ui.base.BaseAdapter
import com.app.mytaxi.ui.base.CommonViewHolder

/**
 * Created by Ajay Deepak on 17-11-2019, 23:06
 */

class TaxiAdapter(private val locationList: ArrayList<Area>, private val context: Context) :
    BaseAdapter<Area, CommonViewHolder>(locationList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return CommonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_taxi, parent, false), context
        )
    }

    fun taxiData(locationList: List<Area>): List<Area> =
        locationList.filter { it.fleetType == "TAXI" }
}