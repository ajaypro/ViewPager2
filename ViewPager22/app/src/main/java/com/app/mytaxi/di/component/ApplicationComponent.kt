package com.app.mytaxi.di.component

import android.app.Application
import com.app.mytaxi.MyTaxiApplication
import com.app.mytaxi.data.NetworkService
import com.app.mytaxi.di.module.ApplicationModule
import com.app.mytaxi.utils.network.NetworkHelper
import com.app.mytaxi.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Ajay Deepak on 25-11-2019, 14:17
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

     // For scanning the Application class for dependencies to provide.
     fun inject(myTaxiApplication: MyTaxiApplication)

    fun getApplication(): Application

    fun getCompositeDisposable():CompositeDisposable

    //fun getGeoLocation(): GeoLocation

    fun getNetworkHelper(): NetworkHelper

    fun getNetworkService(): NetworkService

    fun getScheduleProvider(): SchedulerProvider


}