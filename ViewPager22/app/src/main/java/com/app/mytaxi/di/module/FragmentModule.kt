package com.app.mytaxi.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mytaxi.data.repository.LocationRepository
import com.app.mytaxi.ui.SharedViewModel
import com.app.mytaxi.ui.base.BaseFragment
import com.app.mytaxi.ui.pool.PoolAdapter
import com.app.mytaxi.ui.taxi.TaxiAdapter
import com.app.mytaxi.utils.ViewModelProviderFactory
import com.app.mytaxi.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ajay Deepak on 25-11-2019, 18:20
 */

@Module
class FragmentModule(private val fragment: BaseFragment) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    /*@Provides
    fun providesGeoLocation() = GeoLocation(fragment.context!!)*/


    @Provides
    fun providesTaxiAdapter() = TaxiAdapter(ArrayList(), fragment.context!!)


    @Provides
    fun providesPoolAdapter() = PoolAdapter(ArrayList(), fragment.context!!)

    @Provides
    fun providesSharedViewModel(compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper, locationRepository: LocationRepository)
        : SharedViewModel =
        ViewModelProviders.of(fragment.activity!!, ViewModelProviderFactory(SharedViewModel::class)
        {
            SharedViewModel(
                compositeDisposable,
                networkHelper,
                locationRepository,
                ArrayList()
            )
        }).get(SharedViewModel::class.java)
}