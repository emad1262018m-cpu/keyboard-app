package com.keyboardapp.data.settings

data class KeyboardSettings(
    val fontSize: Int = 32,
    val textColor: Long = 0xFF000000,
    val backgroundColor: Long = 0xFFF0F0F0,
    val borderColor: Long = 0xFFCCCCCC,
    val isBold: Boolean = false,
    val language: String = "en"
)
