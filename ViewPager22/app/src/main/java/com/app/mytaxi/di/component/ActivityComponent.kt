package com.app.mytaxi.di.component

import com.app.mytaxi.di.module.ActivityModule
import com.app.mytaxi.di.scope.ActivityScope
import com.app.mytaxi.ui.MainActivity
import com.app.mytaxi.ui.maps.MapActivity
import dagger.Component

/**
 * Created by Ajay Deepak on 25-11-2019, 17:02
 */

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mapActivity: MapActivity)

}