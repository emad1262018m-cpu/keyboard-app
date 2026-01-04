package com.keyboardapp.core.input

class CursorNavigator(private val isRtl: Boolean) {

    fun moveCursorLeft() {
        if (isRtl) {
            moveForward()
        } else {
            moveBackward()
        }
    }

    fun moveCursorRight() {
        if (isRtl) {
            moveBackward()
        } else {
            moveForward()
        }
    }

    private fun moveForward() {
    }

    private fun moveBackward() {
    }
}
