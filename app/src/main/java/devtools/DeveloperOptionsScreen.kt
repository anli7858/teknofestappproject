package com.zenci.bisiklet.devtools

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DeveloperOptionsScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🛠 Geliştirici Seçenekleri", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(20.dp))

            Button(onClick = { navController.navigate("map") }) {
                Text("📍 Canlı Konum Haritası")
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Bu bölüm yalnızca geliştirici modunu etkinleştiren kişiler tarafından görüntülenebilir.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
