package com.vishnuraj.weatherforecast.domain

import com.vishnuraj.weatherforecast.MockRepositoryResponse
import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.base.infrastructure.LocationManager
import com.vishnuraj.weatherforecast.data.model.WeatherRequest
import com.vishnuraj.weatherforecast.data.model.WeatherResponse
import com.vishnuraj.weatherforecast.data.repository.WeatherRepository
import com.vishnuraj.weatherforecast.data.repository.impl.WeatherRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class WeatherUseCaseTest {
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherUseCase: WeatherUseCase

    @Before
    fun setup() {
        weatherRepository = mockk()
        weatherUseCase = WeatherUseCase(weatherRepository)
    }

    @Test
    fun execute() {
           coEvery { weatherRepository.fetchCurrentLocation() } returns MockRepositoryResponse.getLocation()
           coEvery { weatherRepository.fetchWeatherData(WeatherRequest("-33.865143,151.2099")) } returns MockRepositoryResponse.getWeatherResponseData()

            val weatherResult: Result = runBlocking {
                 weatherUseCase.execute()
            }

            assertNotNull(weatherResult)
            val weatherResponse = (weatherResult as Result.Success<*>).response
            assertTrue(weatherResponse is WeatherResponse)
            assertEquals((weatherResponse as WeatherResponse).timezone, "Australia/Sydney")
            assertTrue(weatherResponse.currently.temperature == 66.04)
    }

}