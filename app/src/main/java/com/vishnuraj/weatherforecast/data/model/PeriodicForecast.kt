package com.vishnuraj.weatherforecast.data.model

data class PeriodicForecast(val summary: String,
                            val icon: String,
                            val data: List<Forecast>)