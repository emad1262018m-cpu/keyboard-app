package com.keyboardapp.core.keyboard

import android.os.SystemClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShiftManager(
    private val doubleTapTimeoutMs: Long = 500L
) {

    private var lastShiftTapTimeMs: Long = 0L

    var state: ShiftState by mutableStateOf(ShiftState.Off)
        private set

    val isShiftActive: Boolean
        get() = state != ShiftState.Off

    fun onShiftTapped() {
        val now = SystemClock.uptimeMillis()

        state = when (state) {
            ShiftState.Off -> ShiftState.On
            ShiftState.On -> {
                if (now - lastShiftTapTimeMs <= doubleTapTimeoutMs) {
                    ShiftState.CapsLock
                } else {
                    ShiftState.Off
                }
            }
            ShiftState.CapsLock -> ShiftState.Off
        }

        lastShiftTapTimeMs = now
    }

    fun onCharacterTyped() {
        if (state == ShiftState.On) {
            state = ShiftState.Off
        }
    }

    fun getDisplayCharacter(raw: String): String {
        if (raw.length != 1) return raw
        val c = raw[0]
        if (!c.isLetter()) return raw

        return if (isShiftActive) {
            raw.uppercase()
        } else {
            raw.lowercase()
        }
    }
}

enum class ShiftState {
    Off,
    On,
    CapsLock
}
