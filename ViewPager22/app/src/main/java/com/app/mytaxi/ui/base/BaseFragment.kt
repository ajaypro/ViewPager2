package com.app.mytaxi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.mytaxi.MyTaxiApplication
import com.app.mytaxi.di.component.DaggerFragmentComponent
import com.app.mytaxi.di.component.FragmentComponent
import com.app.mytaxi.di.module.FragmentModule
import com.app.mytaxi.ui.SharedViewModel
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 18-11-2019, 22:39
 */

abstract class BaseFragment: Fragment() {

     @Inject
     lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setUpLiveDataObservers()
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?) = inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
    }

     private fun buildFragmentComponent() =
         DaggerFragmentComponent.builder()
             .applicationComponent((context!!.applicationContext as MyTaxiApplication).applicationComponent)
             .fragmentModule(FragmentModule(this))
             .build()



    protected open fun setUpLiveDataObservers(){

        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

    }

    /*open fun geoLocation() = GeoLocation(context)

    open fun getlocate(){}*/

    fun showMessage(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT) }

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))


    @LayoutRes
    protected  abstract fun layoutId(): Int

    protected abstract fun setUpView(view: View)

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)
}