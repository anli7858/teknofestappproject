package com.zenci.bisiklet

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.*
import kotlin.random.Random

data class BikeData(
    val speed: Double = 0.0,
    val distance: Double = 0.0,
    val battery: Int = 100
)

class BikeViewModel : ViewModel() {

    private val _bikeData = MutableStateFlow(BikeData())
    val bikeData: StateFlow<BikeData> = _bikeData

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    init {
        scope.launch {
            while (true) {
                delay(1000)
                _bikeData.update { current ->
                    val newSpeed = Random.nextDouble(10.0, 30.0)
                    val newDistance = current.distance + newSpeed / 3600.0
                    val newBattery = (current.battery - 1).coerceAtLeast(0)
                    current.copy(speed = newSpeed, distance = newDistance, battery = newBattery)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
