package com.keyboardapp.core.input

import android.util.Log
import android.view.inputmethod.InputConnection

class TextComposer(private val inputConnection: InputConnection?) {

    var isRtlMode: Boolean = false

    fun insertCharacter(char: String) {
        Log.d(TAG, "Insert character: '$char'")
        inputConnection?.commitText(char, 1)
    }

    fun deleteCharacter() {
        Log.d(TAG, "Delete character")
        inputConnection?.deleteSurroundingText(1, 0)
    }

    fun insertSpace() {
        insertCharacter(" ")
    }

    fun insertNewLine() {
        insertCharacter("\n")
    }

    fun getTextBeforeCursor(length: Int): CharSequence? {
        return inputConnection?.getTextBeforeCursor(length, 0)
    }

    fun commitText(text: String) {
        insertCharacter(text)
    }

    fun deleteText() {
        deleteCharacter()
    }

    fun moveCursor(offset: Int) {
        inputConnection?.commitText("", offset)
    }

    fun getCursorPosition(): Int {
        return 0
    }

    fun finishComposingText() {
        inputConnection?.finishComposingText()
    }

    private companion object {
        const val TAG = "TextComposer"
    }
}
