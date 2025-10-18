package com.zenci.bisiklet.settingclass

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatisticsSettings(
    totalDistance: Float,
    avgSpeed: Float,
    maxSpeed: Float,
    showMap: Boolean,
    onToggleMap: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("📊 Sürüş İstatistikleri", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Toplam Mesafe: %.2f km".format(totalDistance))
        Text("Ortalama Hız: %.1f km/h".format(avgSpeed))
        Text("Maks Hız: %.1f km/h".format(maxSpeed))

        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Haritada Göster")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = showMap,
                onCheckedChange = onToggleMap
            )
        }
    }
}
