package com.vishnuraj.weatherforecast.data.repository.impl

import android.location.Location
import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.base.infrastructure.LocationManager
import com.vishnuraj.weatherforecast.base.infrastructure.Network
import com.vishnuraj.weatherforecast.data.model.WeatherEndPoint
import com.vishnuraj.weatherforecast.data.model.WeatherRequest
import com.vishnuraj.weatherforecast.data.repository.WeatherRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WeatherRepositoryImpl @Inject constructor(private val locationManager: LocationManager): WeatherRepository {

    override suspend fun fetchWeatherData(request: WeatherRequest): Result {

        val response = Network.connect(WeatherEndPoint).getWeatherData(request.location)
        return if (response.isSuccessful)
                    Result.Success(response.body())
                else
                    Result.Failure(Error(response.errorBody().toString()))
    }

    override suspend fun fetchCurrentLocation(): Result = suspendCoroutine {
        locationManager.getCurrentLocation { result ->
            when(result) {
                is Result.Success<*> -> {
                    val location = result.response as Location
                    val locationString = "${location.latitude},${location.longitude}"
                    it.resume(Result.Success(locationString))
                }
                else ->{ it.resume(result) }
            }
        }
    }
}