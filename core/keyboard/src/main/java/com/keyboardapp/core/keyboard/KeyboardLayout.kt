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

data class KeyPosition(
    val char: String,
    val row: Int,
    val col: Int,
    val width: Float = 1.0f,
    val height: Float = 1.0f,
    val keyCode: Int = 0,
    val isSpecialKey: Boolean = false,
    val displayChar: String = char
)
