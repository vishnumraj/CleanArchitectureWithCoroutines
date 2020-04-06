package com.vishnuraj.weatherforecast.data.model

import java.io.Serializable

data class WeatherResponse(val timezone: String,
                           val currently: Forecast,
                           val hourly: PeriodicForecast,
                           val daily: PeriodicForecast): Serializable