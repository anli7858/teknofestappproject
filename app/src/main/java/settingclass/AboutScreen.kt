package com.beyondbike.settingclass

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController, onBack: () -> Unit = {}) {
    var tapCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "🚴 Hakkında",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Uygulama Sürümü: 31.0.0", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Geliştiriciler:",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "-Anıl\n-Ege",
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    tapCount++
                    if (tapCount >= 7) {
                        navController.navigate("developer")
                        tapCount = 0
                    }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { onBack() }) {
                Text(text = "Geri Dön")
            }
        }
    }
}
