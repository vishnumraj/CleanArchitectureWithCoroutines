package com.vishnuraj.weatherforecast.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vishnuraj.weatherforecast.MockRepositoryResponse
import com.vishnuraj.weatherforecast.data.model.WeatherRequest
import com.vishnuraj.weatherforecast.data.model.WeatherState
import com.vishnuraj.weatherforecast.data.repository.WeatherRepository
import com.vishnuraj.weatherforecast.domain.WeatherUseCase
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class WeatherViewModelTest {
    private lateinit var weatherUseCase: WeatherUseCase
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherViewModel: WeatherViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        weatherRepository = mockkClass(WeatherRepository::class)
        weatherUseCase = WeatherUseCase(weatherRepository)
        weatherViewModel = WeatherViewModel(weatherUseCase)
    }

    @After
    fun end() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchWeatherData() {
        coEvery { weatherRepository.fetchCurrentLocation() } returns MockRepositoryResponse.getLocation()
        coEvery { weatherRepository.fetchWeatherData(WeatherRequest("-33.865143,151.2099")) } returns MockRepositoryResponse.getWeatherResponseData()

        assertEquals(weatherViewModel.weatherState.value, WeatherState.Loading)
        dispatcher.runBlockingTest { weatherViewModel.fetchWeatherData() }
        assertTrue(weatherViewModel.weatherState.value is WeatherState.WeatherData)
        assertTrue((weatherViewModel.weatherState.value as WeatherState.WeatherData).weatherData.currently.temperature == 66.04)

    }
}