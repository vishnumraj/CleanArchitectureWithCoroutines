package com.vishnuraj.weatherforecast.base.data

enum class PermissionStatus {
    PROCESSED,
    DENIED
}

enum class PermissionCode(val code: Int) {
    FINE_LOCATION(10)
}