package com.vishnuraj.weatherforecast.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context = application
}