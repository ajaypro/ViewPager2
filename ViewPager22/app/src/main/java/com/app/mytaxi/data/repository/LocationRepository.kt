package com.app.mytaxi.data.repository

import android.location.Location
import com.app.mytaxi.data.NetworkService
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.ui.maps.LocationReceiver
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 19-11-2019, 12:39
 */

class LocationRepository @Inject constructor(private val networkService: NetworkService) {

  fun fetchLocationDetails(): Single<List<Area>> =
      networkService.doLocationCall().map { it.poiList }


    var location: Location? = null
    lateinit var resultReceiver: LocationReceiver


}