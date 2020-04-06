package com.vishnuraj.weatherforecast.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishnuraj.weatherforecast.application.WeatherApplication
import com.vishnuraj.weatherforecast.base.data.PermissionCode
import com.vishnuraj.weatherforecast.base.data.PermissionStatus
import com.vishnuraj.weatherforecast.base.infrastructure.PermissionManager

abstract class BaseActivity: AppCompatActivity() {

    abstract var layoutID: Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID)
        onViewCreated(savedInstanceState)
    }

    abstract fun onViewCreated(savedInstanceState: Bundle?)

    fun requestPermission(code: PermissionCode,
                          permissionStatus: (PermissionStatus)->Unit) {

        PermissionManager.requestPermission(this,
            code) {
            permissionStatus(it)
        }
    }

}