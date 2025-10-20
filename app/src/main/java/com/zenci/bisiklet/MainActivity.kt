package com.beyondbike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.zenci.bisiklet.ui.theme.BisikletTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = BikeViewModel()

        setContent {
            BisikletTheme {
                BikeAppMain(viewModel)
            }
        }

        }
    }

