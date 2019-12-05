package com.app.mytaxi.ui.base

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.mytaxi.R
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.ui.maps.LocationFetchService
import com.app.mytaxi.ui.maps.MapActivity
import com.app.mytaxi.utils.common.Constants
import com.app.mytaxi.utils.common.GeoLocation
import com.app.mytaxi.utils.common.textDisplay
import com.app.mytaxi.utils.log.Logger
import kotlinx.android.synthetic.main.item_view_pool.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Ajay Deepak on 19-11-2019, 17:50
 */

open class CommonViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {

    //abstract fun bind(item: T)

    var geoLocation: GeoLocation? = GeoLocation(context)

    fun bind(area: Area) {

        area.coordinate?.let {
            itemDisplay(geoLocation?.getLocation(it))
        }
        itemView.setOnClickListener {
            openMap(it, area)
        }
    }

    private fun openMap(view: View, area: Area) {
            val intent = Intent(view.context, MapActivity::class.java).apply {
                putExtra(Constants.LAT, area.coordinate?.latitude)
                putExtra(Constants.LONG, area.coordinate?.longtitue)
            }
            view.context.startActivity(intent)
        }


    private fun itemDisplay(location: String?) {

        if (location != null) {
            Logger.d("CVH", location.toString())
            when (itemViewType) {
                R.layout.item_view_pool ->
                    itemView.current_location.append(location)
                else ->
                    itemView.current_location.append(location)
            }
        } else {
            when (itemViewType) {
                R.layout.item_view_pool ->
                    itemView.current_location.visibility = View.GONE
                else ->
                    itemView.current_location.visibility = View.GONE
            }
        }
    }
}