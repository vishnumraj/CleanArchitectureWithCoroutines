package com.vishnuraj.weatherforecast.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishnuraj.weatherforecast.base.di.ViewModelFactory
import com.vishnuraj.weatherforecast.base.di.ViewModelKey
import com.vishnuraj.weatherforecast.presentation.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun weatherViewModel(viewModel: WeatherViewModel): ViewModel

    //Add more ViewModels here
}