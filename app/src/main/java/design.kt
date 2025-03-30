package com.example.salesstudio

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Card
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil3.compose.rememberAsyncImagePainter


@Composable
fun Screen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val installedAppsState = remember { mutableStateOf<List<AppInfo>>(emptyList()) }

    LaunchedEffect(Unit) {
        installedAppsState.value = getAllowedApps(context)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Text(
            "Hello User",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W500,
            fontSize = 50.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(installedAppsState.value) { app ->
                AppCardDesign(app, context)
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppCardDesign(app: AppInfo, context: Context) {
    val painter: Painter = rememberAsyncImagePainter(app.iconDrawable)

    Card(
        modifier = Modifier
            .size(140.dp)
            .padding(8.dp),
        onClick = { launchApp(context, app.packageName) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = app.name,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = app.name,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

fun launchApp(context: Context, packageName: String) {
    val intent = context.packageManager.getLaunchIntentForPackage(packageName)
    if (intent != null) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "App not found or cannot be launched", Toast.LENGTH_SHORT).show()
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun getAllowedApps(context: Context): List<AppInfo> {
    val packageManager = context.packageManager
    val installedApps = packageManager.getInstalledApplications(0)

    installedApps.forEach { app ->
        println("Installed App: ${app.packageName}")
    }


    val allowedPackages = setOf(
        "com.google.android.youtube.tv",
        "com.google.android.youtube.tvmusic",
        "com.android.chrome",
        "com.google.android.videos",
        "com.android.tv.settings"
    )

    return installedApps.mapNotNull { app ->
        if (allowedPackages.contains(app.packageName)) {
            try {
                AppInfo(
                    name = packageManager.getApplicationLabel(app).toString(),
                    packageName = app.packageName,
                    iconDrawable = packageManager.getApplicationIcon(app)
                )
            } catch (e: PackageManager.NameNotFoundException) {
                null
            }
        } else {
            null
        }
    }
}
