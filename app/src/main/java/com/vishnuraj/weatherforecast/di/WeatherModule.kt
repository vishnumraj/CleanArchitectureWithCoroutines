package com.vishnuraj.weatherforecast.di

import android.content.Context
import com.vishnuraj.weatherforecast.base.infrastructure.LocationManager
import com.vishnuraj.weatherforecast.data.repository.WeatherRepository
import com.vishnuraj.weatherforecast.data.repository.impl.WeatherRepositoryImpl
import com.vishnuraj.weatherforecast.domain.WeatherUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class WeatherModule {

    @Provides
    @Inject
    fun providesLocationManager(context: Context): LocationManager = LocationManager(context)

    @Provides
    @Inject
    fun providesWeatherRepository(locationManager: LocationManager): WeatherRepository = WeatherRepositoryImpl(locationManager)

    @Provides
    @Inject
    fun providesWeatherUseCase(repository: WeatherRepository): WeatherUseCase = WeatherUseCase(repository)
}