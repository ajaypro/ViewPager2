package com.app.mytaxi.di.component

import com.app.mytaxi.di.module.FragmentModule
import com.app.mytaxi.di.scope.FragmentScope
import com.app.mytaxi.ui.pool.PoolFragment
import com.app.mytaxi.ui.taxi.TaxiFragment
import dagger.Component

/**
 * Created by Ajay Deepak on 25-11-2019, 18:22
 */

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(taxiFragment: TaxiFragment)
    fun inject(poolFragment: PoolFragment)
}