package com.keyboardapp.data.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface SettingsRepository {
    fun getSettings(): Flow<KeyboardSettings>
    suspend fun updateSettings(settings: KeyboardSettings)
}

class SettingsRepositoryImpl : SettingsRepository {
    override fun getSettings(): Flow<KeyboardSettings> {
        return flowOf(KeyboardSettings())
    }
    
    override suspend fun updateSettings(settings: KeyboardSettings) {
    }
}
