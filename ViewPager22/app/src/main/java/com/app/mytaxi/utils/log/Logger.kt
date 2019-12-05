package com.app.mytaxi.utils.log

import com.app.mytaxi.BuildConfig
import timber.log.Timber

/**
 * Created by Ajay Deepak on 19-11-2019, 13:46
 */

object Logger {


    init {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    fun d(tag: String, s: String, vararg params: Any) =
        Timber.tag(tag).d(s, params)

    fun d(tag: String, throwable: Throwable, s: String, vararg params: Any) =
        Timber.tag(tag).d(throwable, s, params)

    fun e(tag: String, s: String, vararg params: Any) =
        Timber.tag(tag).e(s, params)

    fun e(tag: String, throwable: Throwable, s: String, vararg params: Any) =
        Timber.tag(tag).e(throwable, s, params)
}