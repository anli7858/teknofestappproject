package com.beyondbike

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import com.zenci.bisiklet.devtools.DeveloperOptionsScreen
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beyondbike.screens.HomeScreen
import com.beyondbike.screens.RaceScreen
import com.beyondbike.screens.SpeedScreen
import com.beyondbike.screens.SettingsScreen
import com.beyondbike.settingclass.AboutScreen
import com.beyondbike.settingclass.FeedbackSettings
import com.beyondbike.features.QrScannerScreen
import com.beyondbike.screens.MapScreen
import com.beyondbike.settingclass.EasterEggScreen
import androidx.compose.material.icons.automirrored.filled.DirectionsBike
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
                                "Race" -> Icon(Icons.AutoMirrored.Filled.DirectionsBike, contentDescription = "Race")
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
                composable("developer") { DeveloperOptionsScreen(navController) }
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
