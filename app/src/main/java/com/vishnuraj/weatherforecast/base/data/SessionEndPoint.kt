package com.vishnuraj.weatherforecast.base.data

/**
 * abstract class acting as a base for Network Service request. The class
 * has two parameters baseURL and api representing the Retrofit interface
 * class
 */
abstract class SessionEndPoint<T> {

    /**
     * base URL for the Network request
     */
    val baseURL: String = "https://www.dropbox.com/s/tewg9b71x0wrou9/data.json"

    /**
     * abstract api representing the retrofit class
     */
    abstract val api: Class<out T>

}