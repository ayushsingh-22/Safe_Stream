package com.example.salesstudio

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import com.example.salesstudio.com.example.salesstudio.ExitDialog
import com.example.salesstudio.ui.theme.OverlayPermissionHelper

class MainActivity : ComponentActivity() {

    private var showExitDialog = mutableStateOf(false)
    private var shouldExitToHome = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            OverlayPermissionHelper.requestOverlayPermission(this)
        } else {
            showMainScreen()
        }
    }

    @Deprecated("Deprecated in Java")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OverlayPermissionHelper.REQUEST_OVERLAY_PERMISSION) {
            if (Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "Overlay permission granted.", Toast.LENGTH_LONG).show()
                showMainScreen()
            } else {
                Toast.makeText(this, "Overlay permission denied.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showMainScreen() {
        setContent {
            Screen()
            BackHandler {
                showExitDialog.value = true
                shouldExitToHome = false
            }

            if (showExitDialog.value) {
                ExitDialog(
                    onDismiss = { showExitDialog.value = false },
                    onConfirm = { handleExitAction() }
                )
            }
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        // Show exit dialog on Home button press, but allow PIN protection
        showExitDialog.value = true
        shouldExitToHome = true
    }

    private fun handleExitAction() {
        if (shouldExitToHome) {
            val homeIntent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(homeIntent)
        }
        finish()
    }
}


