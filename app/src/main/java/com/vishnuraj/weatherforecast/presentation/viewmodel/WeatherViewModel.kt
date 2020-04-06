package com.vishnuraj.weatherforecast.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishnuraj.weatherforecast.base.data.Result
import com.vishnuraj.weatherforecast.data.model.WeatherResponse
import com.vishnuraj.weatherforecast.data.model.WeatherState
import com.vishnuraj.weatherforecast.domain.WeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase ) : ViewModel() {

    var weatherState = MutableLiveData<WeatherState>().apply {
        this.value = WeatherState.Loading
    }


    fun fetchWeatherData() {
        viewModelScope.launch {
            when(val result = weatherUseCase.execute()) {
                is Result.Success<*> -> {
                    weatherState.value = WeatherState.WeatherData(result.response as WeatherResponse)
                }
                is Result.Failure -> {
                    weatherState.value = WeatherState.ErrorData(Error("Weather Failed"))
                }
            }
        }
    }
}