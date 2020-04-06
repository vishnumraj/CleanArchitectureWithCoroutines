package com.vishnuraj.weatherforecast.data.repository.impl

import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.base.infrastructure.LocationManager
import com.vishnuraj.weatherforecast.data.model.WeatherRequest
import com.vishnuraj.weatherforecast.data.model.WeatherResponse
import com.vishnuraj.weatherforecast.data.repository.WeatherRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class WeatherRepositoryImplTest {
    private lateinit var locationManager: LocationManager
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup() {
        locationManager = Mockito.mock(LocationManager::class.java)
        weatherRepository = WeatherRepositoryImpl(locationManager)
    }

    @Test
    fun fetchWeatherData() {
        val locationString = "-33.865143,151.209900"
        val weatherResult: Result = runBlocking {
            weatherRepository.fetchWeatherData(WeatherRequest(locationString))
        }

        assertNotNull(weatherResult)
        val weatherResponse = (weatherResult as Result.Success<*>).response
        assertTrue(weatherResponse is WeatherResponse)
        assertEquals((weatherResponse as WeatherResponse).timezone, "Australia/Sydney")
    }
}


