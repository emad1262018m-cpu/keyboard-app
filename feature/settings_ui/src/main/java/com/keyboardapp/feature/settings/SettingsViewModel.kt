package com.keyboardapp.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyboardapp.data.settings.KeyboardSettings
import com.keyboardapp.data.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    
    val settingsFlow: StateFlow<KeyboardSettings> = settingsRepository.getSettingsFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = KeyboardSettings()
        )
    
    fun updateKeyboardFontSize(sizeSp: Int) {
        viewModelScope.launch {
            settingsRepository.updateKeyboardFontSize(sizeSp)
        }
    }
    
    fun updateInputFieldFontSize(sizeSp: Int) {
        viewModelScope.launch {
            settingsRepository.updateInputFieldFontSize(sizeSp)
        }
    }
    
    fun updateFontFamily(family: String) {
        viewModelScope.launch {
            settingsRepository.updateFontFamily(family)
        }
    }
    
    fun updateTextColor(color: String) {
        viewModelScope.launch {
            settingsRepository.updateTextColor(color)
        }
    }
    
    fun updateBackgroundColor(color: String) {
        viewModelScope.launch {
            settingsRepository.updateBackgroundColor(color)
        }
    }
    
    fun updateBorderColor(color: String) {
        viewModelScope.launch {
            settingsRepository.updateBorderColor(color)
        }
    }
    
    fun updateBold(bold: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateBold(bold)
        }
    }
    
    fun updateLanguage(lang: String) {
        viewModelScope.launch {
            settingsRepository.updateLanguage(lang)
        }
    }
    
    fun updateKeyboardHeight(height: Float) {
        viewModelScope.launch {
            settingsRepository.updateKeyboardHeight(height)
        }
    }
}
