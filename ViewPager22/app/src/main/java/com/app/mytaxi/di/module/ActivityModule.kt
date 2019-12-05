package com.app.mytaxi.di.module

import androidx.lifecycle.ViewModelProviders
import com.app.mytaxi.data.NetworkService
import com.app.mytaxi.data.repository.LocationRepository
import com.app.mytaxi.ui.SharedViewModel
import com.app.mytaxi.ui.base.BaseActivity
import com.app.mytaxi.ui.maps.MapViewModel
import com.app.mytaxi.utils.ViewModelProviderFactory
import com.app.mytaxi.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ajay Deepak on 25-11-2019, 16:53
 */

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun providesLocationRepository(networkService: NetworkService) =
        LocationRepository(networkService)

    @Provides
    fun providesSharedViewModel(compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper, locationRepository: LocationRepository)
        : SharedViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(SharedViewModel::class)
        {
            SharedViewModel(
                compositeDisposable,
                networkHelper,
                locationRepository,
                ArrayList()
            )
        }).get(SharedViewModel::class.java)

    @Provides
    fun providesMapViewModel(compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper): MapViewModel =
        ViewModelProviders.of(activity, ViewModelProviderFactory(MapViewModel::class)
        {
            MapViewModel(
                compositeDisposable,
                networkHelper
            )
        }).get(MapViewModel::class.java)
}