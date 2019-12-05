package com.app.mytaxi.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MyTaxiRequest(
    @Expose
    @SerializedName("id")
    var id: Int
)