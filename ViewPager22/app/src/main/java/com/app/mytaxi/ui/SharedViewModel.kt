package com.app.mytaxi.ui

import androidx.lifecycle.MutableLiveData
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.data.repository.LocationRepository
import com.app.mytaxi.di.scope.ActivityScope
import com.app.mytaxi.ui.base.BaseViewModel
import com.app.mytaxi.utils.common.GeoLocation
import com.app.mytaxi.utils.network.NetworkHelper
import com.app.mytaxi.utils.common.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ajay Deepak on 18-11-2019, 20:29
 */

@ActivityScope
class SharedViewModel(compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val locationRepository: LocationRepository,
    private val allLocationList: ArrayList<Area>
) : BaseViewModel(compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val locations: MutableLiveData<Resource<List<Area>>> = MutableLiveData()

    fun getLocationDetails() {
        if (checkInternetConnectionWithMessage()) {
            loading.postValue(true)
            compositeDisposable.add(
                locationRepository
                    .fetchLocationDetails()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            allLocationList.addAll(it)
                            /*taxiArrayList = allLocationList.filter { area ->
                                return@filter area.fleetType == "TAXI"
                            }
                            poolArrayList = allLocationList.filter { area ->
                                return@filter area.fleetType == "POOLING"
                            }

                            allLocationList.addAll(taxiArrayList)
                            allLocationList.addAll(poolArrayList)*/

                            loading.postValue(false)
                            locations.postValue(Resource.success(it))
                        },
                        {
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }

    /*private fun getVehicleLocation(locationList: ArrayList<Area>): List<Area> {

        taxiArrayList = locationList.filter { it.fleetType == "TAXI" }
        poolArrayList = locationList.filter { it.fleetType == "POOLING" }

        return taxiArrayList
    }*/

    private fun getData() {

        if (allLocationList.isEmpty()) {
            getLocationDetails()
        } else {
            allLocationList
        }
    }

    override fun onCreate() {
        getData()
    }
}