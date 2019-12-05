package com.app.mytaxi.data

import com.app.mytaxi.BuildConfig
import com.app.mytaxi.data.remote.response.LocationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(BuildConfig.BASE_URL)
    fun doLocationCall(
        @Query("p1Lat") p1Lat: Double? = Networking.P1_LAT,
        @Query("p1Lon") p1Lon: Double? = Networking.P1_LON,
        @Query("p2Lat") p2Lat: Double? = Networking.P2_LAT,
        @Query("p2Lon") p2Lon: Double? = Networking.P2_LON
    ): Single<LocationResponse>


}