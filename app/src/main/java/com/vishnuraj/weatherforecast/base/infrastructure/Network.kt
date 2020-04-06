package com.vishnuraj.weatherforecast.base.infrastructure

import com.vishnuraj.weatherforecast.base.data.SessionEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    fun <T> connect(endPoint: SessionEndPoint<T>): T {
        return Retrofit.Builder()
            .baseUrl(endPoint.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(endPoint.api)
    }
}