package com.app.mytaxi.ui.maps

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.ResultReceiver
import androidx.core.app.JobIntentService
import com.app.mytaxi.R
import com.app.mytaxi.data.model.Area
import com.app.mytaxi.utils.common.Constants
import com.app.mytaxi.utils.log.Logger
import timber.log.Timber
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.Locale

/**
 * Created by Ajay Deepak on 28-11-2019, 23:47
 */

class LocationFetchService : JobIntentService() {

    private val TAG = "FetchAddressService"
    /**
     * The receiver where results are forwarded from this service.
     */
    private var receiver: ResultReceiver? = null

    override fun onHandleWork(intent: Intent) {

        var errorMessage = ""
        var addresses: List<Address> = emptyList()

        val geocoder = Geocoder(this, Locale.GERMANY)
        val location = intent.getParcelableExtra<Location>("sdfsd")

        try {
            location?.let {
                addresses = geocoder.getFromLocation(it.latitude, it.longitude, 2)
            }
        } catch (ioException: IOException) {
            errorMessage = getString(R.string.no_service)
            Logger.e(TAG, errorMessage, ioException)
        } catch (illegalArgumentException: IllegalArgumentException) {
            errorMessage = getString(R.string.invalid_coordinates)
            Logger.e(
                TAG, "$errorMessage. Latitude = $location.latitude , " +
                    "Longitude =  $location.longitude", illegalArgumentException
            )
        }

        when {
            addresses.isEmpty() -> {
                if (errorMessage.isEmpty()) {
                    errorMessage = getString(R.string.invalid_coordinates)
                    Logger.e(TAG, errorMessage)
                }
                deliverResultToReceiver(1, errorMessage)
            }
            else -> {
                val locations = addresses[0]
                val locality = with(locations) {
                    (0..maxAddressLineIndex).map { locality }
                }
                Logger.e(TAG, getString(R.string.location_found))
                deliverResultToReceiver(
                    0,
                    locality.joinToString(separator = "\n")
                )
            }
        }
    }

    private fun deliverResultToReceiver(resultCode: Int, message: String) {
        val bundle = Bundle().apply { putString("sdfsad", message) }
        receiver?.send(resultCode, bundle)
    }
}