package com.vishnuraj.weatherforecast.application

import android.app.Application
import com.vishnuraj.weatherforecast.di.AppComponent
import com.vishnuraj.weatherforecast.di.AppModule
import com.vishnuraj.weatherforecast.di.DaggerAppComponent

class WeatherApplication : Application() {

    lateinit var component:AppComponent

    override fun onCreate() {
        super.onCreate()
        component = getComponent(this)
    }

    private fun getComponent(weatherApplication: WeatherApplication): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(weatherApplication))
            .build()
    }

}