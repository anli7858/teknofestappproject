package com.zenci.bisiklet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zenci.bisiklet.screens.HomeScreen
import com.zenci.bisiklet.screens.RaceScreen
import com.zenci.bisiklet.screens.SpeedScreen
import com.zenci.bisiklet.screens.SettingsScreen
import com.zenci.bisiklet.settingclass.AboutScreen
import com.zenci.bisiklet.settingclass.FeedbackSettings
import com.zenci.bisiklet.features.QrScannerScreen
import com.zenci.bisiklet.screens.MapScreen
import com.zenci.bisiklet.settingclass.EasterEggScreen
@Composable
fun BikeAppMain(viewModel: BikeViewModel) {
    val navController = rememberNavController()
    val tabs = listOf("Home", "Race", "Speed", "Settings", "QR")
    var selectedTab by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        icon = {
                            when (tab) {
                                "Home" -> Icon(Icons.Default.Home, contentDescription = "Home")
                                "Race" -> Icon(Icons.Default.DirectionsBike, contentDescription = "Race")
                                "Speed" -> Icon(Icons.Default.Speed, contentDescription = "Speed")
                                "Settings" -> Icon(Icons.Default.Settings, contentDescription = "Settings")
                                "QR" -> Icon(Icons.Default.QrCodeScanner, contentDescription = "QR Scanner")
                                "Map" -> Icon(Icons.Default.Home, contentDescription = "Map")
                            }
                        },
                        label = { Text(tab) },
                        selected = tab == selectedTab,
                        onClick = {
                            selectedTab = tab
                            navController.navigate(tab.lowercase()) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("map") { MapScreen() }
                composable("easteregg") { EasterEggScreen(navController) }
                composable("home") { HomeScreen(viewModel) }
                composable("race") { RaceScreen(viewModel) }
                composable("speed") { SpeedScreen() }
                composable("settings") { SettingsScreen(navController) }
                composable("about") { AboutScreen(navController = navController, onBack = { navController.popBackStack() }) }
                composable("feedback") {
                    FeedbackSettings(onSendFeedback = {
                        println("Geri bildirim gönderildi!")
                    })
                }
                composable("qr") {
                    QrScannerScreen(onQrScanned = { code ->
                        println("Taranan Kod: $code")
                    })
                }
            }
        }
    }
}
