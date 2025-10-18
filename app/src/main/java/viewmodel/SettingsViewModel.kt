package com.zenci.bisiklet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zenci.bisiklet.data.SettingsDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val store = SettingsDataStore(application)


    val darkTheme: StateFlow<Boolean> = store.darkThemeFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
    val notifications: StateFlow<Boolean> = store.notificationsFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, true)
    val speedUnit: StateFlow<String> = store.speedUnitFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, "km/h")

    val aboutVisible = MutableStateFlow(false)
    val mapTrackingEnabled = MutableStateFlow(false)
    val avgSpeed = MutableStateFlow(0.0)
    val maxSpeed = MutableStateFlow(0.0)
    val totalDistance = MutableStateFlow(0.0)
    val feedbackEnabled = MutableStateFlow(false)


    fun setDarkTheme(enabled: Boolean) = viewModelScope.launch { store.setDarkTheme(enabled) }
    fun setNotifications(enabled: Boolean) = viewModelScope.launch { store.setNotifications(enabled) }
    fun setSpeedUnit(unit: String) = viewModelScope.launch { store.setSpeedUnit(unit) }

    fun toggleAbout() { aboutVisible.value = !aboutVisible.value }
    fun setMapTracking(enabled: Boolean) { mapTrackingEnabled.value = enabled }
    fun updateAvgSpeed(speed: Double) { avgSpeed.value = speed }
    fun updateMaxSpeed(speed: Double) { maxSpeed.value = speed }
    fun updateTotalDistance(distance: Double) { totalDistance.value = distance }
    fun setFeedbackEnabled(enabled: Boolean) { feedbackEnabled.value = enabled }
}
