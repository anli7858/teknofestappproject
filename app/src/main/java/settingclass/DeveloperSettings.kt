package com.beyondbike.settingclass

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment



@Composable
fun DeveloperSettings(
    debugMode: Boolean,
    onDebugModeChange: (Boolean) -> Unit,
    testDataEnabled: Boolean,
    onTestDataChange: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("👨‍💻 Geliştirici Ayarları", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Debug Loglarını Aç")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = debugMode, onCheckedChange = onDebugModeChange)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Test Verilerini Kullan")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = testDataEnabled, onCheckedChange = onTestDataChange)
        }
    }
}
