package com.vishnuraj.weatherforecast.domain

import android.location.Location
import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.base.domain.UseCase
import com.vishnuraj.weatherforecast.data.model.WeatherRequest
import com.vishnuraj.weatherforecast.data.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: WeatherRepository) : UseCase<WeatherRequest, Result> {

    override suspend fun execute(params: WeatherRequest?): Result {
        return when(val locationResult = repository.fetchCurrentLocation()) {
            is Result.Success<*> -> { fetchWeatherData(locationResult.response as String) }
            is Result.Failure -> { locationResult }
        }
    }

    private suspend fun fetchWeatherData(locationString: String): Result {
        return repository.fetchWeatherData(WeatherRequest(locationString))
    }

}