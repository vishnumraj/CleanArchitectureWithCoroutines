package com.vishnuraj.weatherforecast.base.infrastructure

import android.content.Context
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.data.model.LocationError
import javax.inject.Inject

class LocationManager @Inject constructor(val context: Context) {

    fun getCurrentLocation(result: (Result)-> Unit) {
        val locationClient = LocationServices.getFusedLocationProviderClient(context)
        locationClient.lastLocation.addOnCompleteListener{
            when {
                it.isSuccessful-> {
                    it.result?.let {location->
                        result(Result.Success(location))
                    }?:run {
                        requestLocationUpdate(result)
                    }

                }
                it.isCanceled-> { result(Result.Failure(LocationError("Location canceled")))}
            }
        }
    }

    private fun requestLocationUpdate(result: (Result)->Unit){
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1

        val locationClient = LocationServices.getFusedLocationProviderClient(context)
        locationClient.requestLocationUpdates(locationRequest, object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.lastLocation?.let {
                    result(Result.Success(it))
                }?:run {
                    result(Result.Failure(Error("Fetch Location Failed")))
                }
            }
        }, Looper.myLooper())

    }


}