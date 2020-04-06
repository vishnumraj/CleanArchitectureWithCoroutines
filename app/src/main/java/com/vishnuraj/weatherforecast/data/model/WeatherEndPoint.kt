package com.vishnuraj.weatherforecast.data.model

import com.vishnuraj.weatherforecast.base.data.SessionEndPoint
import com.vishnuraj.weatherforecast.infrastructure.WeatherAPI

object WeatherEndPoint : SessionEndPoint<WeatherAPI>() {

    override val api: Class<out WeatherAPI> = WeatherAPI::class.java
}