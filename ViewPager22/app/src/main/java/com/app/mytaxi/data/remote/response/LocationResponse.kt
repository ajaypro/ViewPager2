package com.app.mytaxi.data.remote.response

import com.app.mytaxi.data.model.Area
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ajay Deepak on 18-11-2019, 16:10
 */

data class LocationResponse(

    @Expose
    @SerializedName("poiList")
     var poiList: List<Area>
)