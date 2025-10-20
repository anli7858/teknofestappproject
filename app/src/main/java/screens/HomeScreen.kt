package com.beyondbike.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beyondbike.BikeViewModel

@Composable
fun HomeScreen(viewModel: BikeViewModel) {
    val data by viewModel.bikeData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🚴 Bisiklet",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text("Hız: ${"%.1f".format(data.speed)} km/h", fontSize = 22.sp)
        Text("Mesafe: ${"%.2f".format(data.distance)} km", fontSize = 22.sp)
        Text("Batarya: ${data.battery}%", fontSize = 22.sp)
    }
}
