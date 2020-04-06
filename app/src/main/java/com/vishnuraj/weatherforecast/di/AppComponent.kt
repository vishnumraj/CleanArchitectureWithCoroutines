package com.vishnuraj.weatherforecast.di

import com.vishnuraj.weatherforecast.presentation.activity.WeatherActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,WeatherModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(target: WeatherActivity)
}