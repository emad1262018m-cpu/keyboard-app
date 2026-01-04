package com.keyboardapp.data.settings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keyboard_layouts")
data class KeyboardLayoutEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,              // e.g., "MyLargeQWERTY"
    val layoutJson: String,        // Serialized layout (List<KeyPosition>)
    val language: String,          // "en" or "ar"
    val isDefault: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
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