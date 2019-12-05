package com.app.mytaxi.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ajay Deepak on 18-11-2019, 15:38
 */

data class Area(
    @Expose
    @SerializedName("id")
    var id: Int,

    @Expose
    @SerializedName("coordinate")
    var coordinate: Coordinates?,

    @Expose
    @SerializedName("fleetType")
    var fleetType: String,

    @Expose
    @SerializedName("heading")
    var heading: Double
) {

    data class Coordinates(
        @Expose
        @SerializedName("latitude")
        var latitude: Double,

        @Expose
        @SerializedName("longitude")
        var longtitue: Double

    )


}





