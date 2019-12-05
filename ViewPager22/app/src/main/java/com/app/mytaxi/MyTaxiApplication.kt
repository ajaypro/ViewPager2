package com.app.mytaxi

import android.app.Application
import com.app.mytaxi.di.component.ApplicationComponent
import com.app.mytaxi.di.component.DaggerApplicationComponent
import com.app.mytaxi.di.module.ApplicationModule

/**
 * Created by Ajay Deepak on 25-11-2019, 14:14
 */

class MyTaxiApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies(){

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
            applicationComponent.inject(this)

    }

    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}