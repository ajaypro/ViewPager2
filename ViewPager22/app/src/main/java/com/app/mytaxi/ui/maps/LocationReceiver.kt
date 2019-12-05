package com.app.mytaxi.ui.maps

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

/**
 * Created by Ajay Deepak on 29-11-2019, 14:28
 */

class LocationReceiver constructor(handler: Handler):ResultReceiver(handler) {

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        super.onReceiveResult(resultCode, resultData)

    }
}