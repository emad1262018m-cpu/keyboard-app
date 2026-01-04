package com.keyboardapp.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val dataStore: DataStore<Preferences>) {
    
    companion object {
        private val KEYBOARD_FONT_SIZE = intPreferencesKey("keyboard_font_size")
        private val INPUT_FIELD_FONT_SIZE = intPreferencesKey("input_field_font_size")
        private val FONT_FAMILY = stringPreferencesKey("font_family")
        private val TEXT_COLOR = stringPreferencesKey("text_color")
        private val BACKGROUND_COLOR = stringPreferencesKey("background_color")
        private val BORDER_COLOR = stringPreferencesKey("border_color")
        private val IS_BOLD = booleanPreferencesKey("is_bold")
        private val LANGUAGE = stringPreferencesKey("language")
        private val KEYBOARD_HEIGHT = floatPreferencesKey("keyboard_height")
    }
    
    fun getSettingsFlow(): Flow<KeyboardSettings> {
        return dataStore.data.map { prefs ->
            KeyboardSettings(
                keyboardFontSizeSp = prefs[KEYBOARD_FONT_SIZE] ?: 32,
                inputFieldFontSizeSp = prefs[INPUT_FIELD_FONT_SIZE] ?: 28,
                fontFamily = prefs[FONT_FAMILY] ?: "Roboto",
                textColor = prefs[TEXT_COLOR] ?: "#000000",
                backgroundColor = prefs[BACKGROUND_COLOR] ?: "#F0F0F0",
                borderColor = prefs[BORDER_COLOR] ?: "#CCCCCC",
                isBold = prefs[IS_BOLD] ?: false,
                language = prefs[LANGUAGE] ?: "en",
                keyboardHeightDp = prefs[KEYBOARD_HEIGHT] ?: 250f
            )
        }
    }
    
    suspend fun updateKeyboardFontSize(sizeSp: Int) {
        dataStore.edit { prefs ->
            prefs[KEYBOARD_FONT_SIZE] = sizeSp
        }
    }
    
    suspend fun updateInputFieldFontSize(sizeSp: Int) {
        dataStore.edit { prefs ->
            prefs[INPUT_FIELD_FONT_SIZE] = sizeSp
        }
    }
    
    suspend fun updateFontFamily(family: String) {
        dataStore.edit { prefs ->
            prefs[FONT_FAMILY] = family
        }
    }
    
    suspend fun updateTextColor(color: String) {
        dataStore.edit { prefs ->
            prefs[TEXT_COLOR] = color
        }
    }
    
    suspend fun updateBackgroundColor(color: String) {
        dataStore.edit { prefs ->
            prefs[BACKGROUND_COLOR] = color
        }
    }
    
    suspend fun updateBorderColor(color: String) {
        dataStore.edit { prefs ->
            prefs[BORDER_COLOR] = color
        }
    }
    
    suspend fun updateBold(bold: Boolean) {
        dataStore.edit { prefs ->
            prefs[IS_BOLD] = bold
        }
    }
    
    suspend fun updateLanguage(lang: String) {
        dataStore.edit { prefs ->
            prefs[LANGUAGE] = lang
        }
    }
    
    suspend fun updateKeyboardHeight(height: Float) {
        dataStore.edit { prefs ->
            prefs[KEYBOARD_HEIGHT] = height
        }
    }
}
