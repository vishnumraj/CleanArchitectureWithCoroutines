package com.vishnuraj.weatherforecast

import android.location.Location
import android.location.LocationManager
import com.google.gson.Gson
import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.data.model.WeatherResponse
import io.mockk.mockk
import java.io.File


object MockRepositoryResponse {

    fun getWeatherResponseData(): Result {
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource("WeatherForecast.json")
        val file = File(resource!!.path)
        val jsonString = file.readText(Charsets.UTF_8)
        val response = Gson().fromJson(jsonString, WeatherResponse::class.java)
        return Result.Success(response)
    }

    fun getLocation(): Result {
        val latitude = -33.865143
        val longitude = 151.209900
        return Result.Success("$latitude,$longitude")
    }
}