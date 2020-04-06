package com.vishnuraj.weatherforecast.infrastructure

import com.vishnuraj.weatherforecast.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {

    @GET("{location}")
    suspend fun getWeatherData(
        @Path("location")
        location: String
    ) : Response<WeatherResponse>
}