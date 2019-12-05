package com.app.mytaxi.di.module

import android.app.Application
import android.content.Context
import com.app.mytaxi.BuildConfig
import com.app.mytaxi.MyTaxiApplication
import com.app.mytaxi.data.NetworkService
import com.app.mytaxi.data.Networking
import com.app.mytaxi.di.ApplicationContext
import com.app.mytaxi.utils.network.NetworkHelper
import com.app.mytaxi.utils.rx.RxSchedulerProvider
import com.app.mytaxi.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Ajay Deepak on 25-11-2019, 14:18
 */

@Module
class ApplicationModule(private val application: MyTaxiApplication) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun providesApplication(): Application = application

    @Singleton
    @Provides
    @ApplicationContext
    fun providesContext(): Context = application

    @Singleton
    @Provides
    fun providesNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 10 * 1024    // 10mb
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    /*@Provides
    fun providesGeoLocation(): GeoLocation = GeoLocation(application)*/

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()
}
