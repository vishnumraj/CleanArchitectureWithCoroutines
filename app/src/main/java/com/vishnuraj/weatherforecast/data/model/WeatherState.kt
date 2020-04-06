package com.vishnuraj.weatherforecast.data.model

import android.location.Location

sealed class WeatherState {
    object Loading : WeatherState()
    class WeatherData(val weatherData: WeatherResponse) : WeatherState()
    class ErrorData(val error: Error): WeatherState()
}