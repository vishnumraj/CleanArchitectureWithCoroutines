package com.vishnuraj.weatherforecast.presentation.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vishnuraj.weatherforecast.R
import com.vishnuraj.weatherforecast.application.WeatherApplication
import com.vishnuraj.weatherforecast.base.presentation.BaseActivity
import com.vishnuraj.weatherforecast.base.data.PermissionCode
import com.vishnuraj.weatherforecast.base.data.PermissionStatus
import com.vishnuraj.weatherforecast.data.model.WeatherResponse
import com.vishnuraj.weatherforecast.data.model.WeatherState
import com.vishnuraj.weatherforecast.presentation.viewmodel.WeatherViewModel
import javax.inject.Inject

class WeatherActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherViewModel

    override var layoutID: Int = R.layout.activity_weather

    override fun onViewCreated(savedInstanceState: Bundle?) {
        (application as WeatherApplication).component.inject(this)

        viewModel =  viewModelFactory.create(WeatherViewModel::class.java)
        viewModel.weatherState.observe(this, Observer(::onWeatherStateChange))

        requestPermission(PermissionCode.FINE_LOCATION) {
            when (it) {
                PermissionStatus.PROCESSED -> { }
                PermissionStatus.DENIED -> { handleError(Error("Loation Permissin Denied")) }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {

        if (requestCode == PermissionCode.FINE_LOCATION.code
            && grantResults.isNotEmpty()
            && grantResults.first() == PackageManager.PERMISSION_GRANTED ) {
            Toast.makeText(this,"Location Permission granted", Toast.LENGTH_SHORT).show()
            viewModel.fetchWeatherData()
        } else {
            Toast.makeText(this,"Location Permission denied", Toast.LENGTH_SHORT).show()
        }

    }

    private fun onWeatherStateChange(state: WeatherState?) {
        when(state) {
            is WeatherState.Loading -> { showLoading(true) }
            is WeatherState.ErrorData -> { handleError(state.error) }
            is WeatherState.WeatherData -> { handleSuccess(state.weatherData) }
        }
    }

    private fun showLoading(show: Boolean){
        val loadingText = if(show) "loading" else "hide loading"
        Toast.makeText(this,loadingText, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(error: Error) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess(response: WeatherResponse) {
        showLoading(false)
        Toast.makeText(this, response.toString(),Toast.LENGTH_SHORT).show()
    }

}
