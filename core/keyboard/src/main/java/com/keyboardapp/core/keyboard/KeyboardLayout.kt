package com.keyboardapp.core.keyboard

data class KeyboardLayout(
    val rows: List<List<KeyData>>
)

data class KeyData(
    val char: String,
    val keyCode: Int,
    val width: Float = 1.0f,
    val isSpecialKey: Boolean = false,
    val displayChar: String = char
)
