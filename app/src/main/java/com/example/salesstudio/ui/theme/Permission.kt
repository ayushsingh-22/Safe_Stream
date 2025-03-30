package com.example.salesstudio.ui.theme

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.core.net.toUri

object OverlayPermissionHelper {

    private fun hasOverlayPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(context)
        } else {
            true
        }
    }

    fun requestOverlayPermission(activity: Activity) {
        if (!hasOverlayPermission(activity)) {
            AlertDialog.Builder(activity)
                .setTitle("Overlay Permission Required")
                .setMessage("This app needs overlay permission to function properly. Do you want to allow it?")
                .setPositiveButton("Yes") { _, _ ->
                    openOverlaySettings(activity)
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        } else {
            Toast.makeText(activity, "Overlay permission already granted.", Toast.LENGTH_LONG).show()
        }
    }

    private fun openOverlaySettings(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                "package:${activity.packageName}".toUri()
            )
            activity.startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION)
        }
    }

    const val REQUEST_OVERLAY_PERMISSION = 101
}

