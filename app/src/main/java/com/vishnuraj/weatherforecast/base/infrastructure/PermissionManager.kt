package com.vishnuraj.weatherforecast.base.infrastructure

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.vishnuraj.weatherforecast.R
import com.vishnuraj.weatherforecast.base.data.PermissionCode
import com.vishnuraj.weatherforecast.base.data.PermissionStatus

object PermissionManager {

    fun requestPermission(activity: Activity,
                          permissionCode: PermissionCode,
                          permissionStatus: (PermissionStatus) -> Unit) {
        val permission = getPermission(permissionCode)
        ContextCompat.checkSelfPermission(activity, permission)

        when {

            ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                                                permission)->{
                showPermissionDialog(
                    activity,
                    permissionCode,
                    permission,
                    permissionStatus
                )
            }
            else -> {
                processPermissionRequest(
                    activity,
                    permissionCode,
                    permission,
                    permissionStatus
                )
            }
        }
    }

    fun showPermissionDialog(activity: Activity,
                             code: PermissionCode,
                             permission: String,
                             permissionStatus: (PermissionStatus) -> Unit){
        AlertDialog.Builder(activity)
            .setMessage(
                getPermissionString(
                    PermissionCode.FINE_LOCATION
                )
            )
            .setPositiveButton(R.string.okText) { dialog, int ->
                processPermissionRequest(
                    activity,
                    code,
                    permission,
                    permissionStatus
                )
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun getPermissionString(code: PermissionCode): Int {
        return when(code) {
            PermissionCode.FINE_LOCATION -> { R.string.locationPermissionMessage }
        }
    }

    private fun getPermission(code: PermissionCode): String {
       return when(code) {
            PermissionCode.FINE_LOCATION -> android.Manifest.permission.ACCESS_FINE_LOCATION
        }
    }


    fun processPermissionRequest(activity: Activity,
                                 permissionCode: PermissionCode,
                                 permission: String,
                                 permissionStatus: (PermissionStatus) -> Unit) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), permissionCode.code)
        permissionStatus(PermissionStatus.PROCESSED)
    }
}