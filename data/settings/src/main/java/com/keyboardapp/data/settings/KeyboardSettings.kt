package com.keyboardapp.data.settings

data class KeyboardSettings(
    val keyboardFontSizeSp: Int = 32,        // Key text size
    val inputFieldFontSizeSp: Int = 28,      // Input field text size
    val fontFamily: String = "Roboto",       // "Roboto", "Arial", "Noto Sans Arabic"
    val textColor: String = "#000000",       // Hex format
    val backgroundColor: String = "#F0F0F0",
    val borderColor: String = "#CCCCCC",
    val isBold: Boolean = false,
    val language: String = "en",             // "en" or "ar"
    val keyboardHeightDp: Float = 250f       // For resizing (Task 5)
)
