package com.app.mytaxi.utils.common

import android.content.Context
import android.location.Geocoder
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.utils.log.Logger
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale.GERMAN

/**
 * Created by Ajay Deepak on 26-11-2019, 18:00
 */

class GeoLocation(context: Context) {


    fun getLocation(coordinates: Area.Coordinates) =
        Single.just(locality(coordinates))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturnItem(null).toString()
            //.blockingGet()




    /*suspend fun getLocation(coordinates: Area.Coordinates): String  =

        CoroutineScope {

            withContext()

     val location = { locality(coordinates) }
    }*/

    //fun getData(string: String): String = string

    private  val geocoder = Geocoder(context, GERMAN)

    fun locality(coordinates: Area.Coordinates) =

         geocoder.getFromLocation(coordinates.latitude, coordinates.longtitue, 3)
                .get(0).subLocality

}



