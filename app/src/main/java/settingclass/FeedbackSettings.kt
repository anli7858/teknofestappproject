package com.zenci.bisiklet.settingclass

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedbackSettings(onSendFeedback: (String) -> Unit) {
    var feedbackText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("✉️ Geri Bildirim", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = feedbackText,
            onValueChange = { feedbackText = it },
            label = { Text("Mesajınızı yazın") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onSendFeedback(feedbackText) }) {
            Text("Gönder")
        }
    }
}
