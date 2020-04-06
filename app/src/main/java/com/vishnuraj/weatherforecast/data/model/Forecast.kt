package com.vishnuraj.weatherforecast.data.model

import java.io.Serializable

data class Forecast(val time: Long,
                    val summary: String,
                    val icon: String,
                    val temperature: Double): Serializable