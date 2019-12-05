package com.app.mytaxi.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.mytaxi.MyTaxiApplication
import com.app.mytaxi.di.component.ActivityComponent
import com.app.mytaxi.di.component.DaggerActivityComponent
import com.app.mytaxi.di.module.ActivityModule
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 25-11-2019, 11:47
 */

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        setUpView(savedInstanceState)
        setUpObservers()
        viewModel.onCreate()
    }

    private fun setUpObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    private fun buildActivityComponent() =

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyTaxiApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    fun showMessage(message: String) =
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setUpView(savedInstanceState: Bundle?)
}
