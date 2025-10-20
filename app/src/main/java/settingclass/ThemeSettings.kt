package com.beyondbike.settingclass

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun ThemeSettings(darkTheme: Boolean, onToggleTheme: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Light")
        Switch(checked = darkTheme, onCheckedChange = onToggleTheme, modifier = Modifier.padding(horizontal = 8.dp))
        Text("Dark")
    }
}
