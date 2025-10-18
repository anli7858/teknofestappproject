package com.zenci.bisiklet.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    private object PreferencesKeys {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val NOTIFICATIONS = booleanPreferencesKey("notifications")
        val SPEED_UNIT = stringPreferencesKey("speed_unit")
    }

    val darkThemeFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[PreferencesKeys.DARK_THEME] ?: false }

    val notificationsFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[PreferencesKeys.NOTIFICATIONS] ?: true }

    val speedUnitFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[PreferencesKeys.SPEED_UNIT] ?: "km/h" }

    suspend fun setDarkTheme(enabled: Boolean) {
        context.dataStore.edit { it[PreferencesKeys.DARK_THEME] = enabled }
    }

    suspend fun setNotifications(enabled: Boolean) {
        context.dataStore.edit { it[PreferencesKeys.NOTIFICATIONS] = enabled }
    }

    suspend fun setSpeedUnit(unit: String) {
        context.dataStore.edit { it[PreferencesKeys.SPEED_UNIT] = unit }
    }
}
