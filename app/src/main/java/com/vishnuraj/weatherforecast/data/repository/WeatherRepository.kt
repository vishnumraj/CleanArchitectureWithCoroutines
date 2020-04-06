package com.vishnuraj.weatherforecast.data.repository

import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.base.infrastructure.LocationManager
import com.vishnuraj.weatherforecast.base.infrastructure.Network
import com.vishnuraj.weatherforecast.data.model.WeatherEndPoint
import com.vishnuraj.weatherforecast.data.model.WeatherRequest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface WeatherRepository {

    suspend fun fetchWeatherData(request: WeatherRequest): Result

    suspend fun fetchCurrentLocation(): Result

}