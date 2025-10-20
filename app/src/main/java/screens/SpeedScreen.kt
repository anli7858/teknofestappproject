package com.beyondbike.screens

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.beyondbike.SpeedViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeedScreen(speedViewModel: SpeedViewModel = viewModel()) {
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val speed by speedViewModel.speed.collectAsState()

    // Kullanıcı birimi seçimi: km/h veya mph
    var useMph by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (locationPermissionState.status.isGranted) {
            speedViewModel.startLocationUpdates()
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🚴 Anlık Hızın",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (useMph)
                    String.format("%.1f mph", speed * 0.621371)
                else
                    String.format("%.1f km/h", speed),
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { useMph = !useMph }) {
                Text(if (useMph) "Show km/h" else "Show mph")
            }
        }
    }
}
