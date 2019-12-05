package com.app.mytaxi.ui.maps

import android.os.Bundle
import com.app.mytaxi.R
import com.app.mytaxi.di.component.ActivityComponent
import com.app.mytaxi.ui.base.BaseActivity
import com.app.mytaxi.utils.common.Constants
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by Ajay Deepak on 27-11-2019, 14:23
 */

class MapActivity : BaseActivity<MapViewModel>(), OnMapReadyCallback {


    companion object {
        val TAG = MapActivity::class.java.simpleName
        const val REQUEST_LOCATION_PERMISSION = 1
    }


    override fun layoutId() = R.layout.activity_maps

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpView(savedInstanceState: Bundle?) {

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val bundle = intent.extras

        googleMap.apply {
            // get lat and long from fragments
            val coordinates =
                LatLng(bundle!!.getDouble(Constants.LAT), bundle.getDouble(Constants.LONG))
            addMarker(MarkerOptions().position(coordinates))
        }
    }
}