package com.beyondbike.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.beyondbike.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController) {
    val vm: SettingsViewModel = viewModel()

    val darkTheme by vm.darkTheme.collectAsState()
    val notifications by vm.notifications.collectAsState()
    val speedUnit by vm.speedUnit.collectAsState()
    val mapTrackingEnabled by vm.mapTrackingEnabled.collectAsState()
    val feedbackEnabled by vm.feedbackEnabled.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("⚙️ Ayarlar", fontSize = 28.sp)
            }

            item {
                Text("Tema Seçimi", fontSize = 20.sp)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Light")
                    Switch(
                        checked = darkTheme,
                        onCheckedChange = { vm.setDarkTheme(it) },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text("Dark")
                }
            }

            item {
                Text("Hız Birimi", fontSize = 20.sp)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                    Button(
                        onClick = { vm.setSpeedUnit("km/h") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (speedUnit == "km/h") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    ) { Text("km/h") }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { vm.setSpeedUnit("mph") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (speedUnit == "mph") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    ) { Text("mph") }
                }
            }

            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Bildirimler")
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = notifications,
                        onCheckedChange = { vm.setNotifications(it) }
                    )
                }
            }

            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Harita Tabanlı Sürüş")
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = mapTrackingEnabled,
                        onCheckedChange = { vm.setMapTracking(it) }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("feedback") }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "📝 Geri Bildirim",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("about") }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ℹ️ Hakkında",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
