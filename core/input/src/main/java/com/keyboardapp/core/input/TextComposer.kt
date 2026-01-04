package com.keyboardapp.core.input

import android.view.inputmethod.InputConnection

class TextComposer(private val inputConnection: InputConnection?) {
    
    var isRtlMode: Boolean = false
    
    fun commitText(text: String) {
        inputConnection?.commitText(text, 1)
    }
    
    fun deleteText() {
        inputConnection?.deleteSurroundingText(1, 0)
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
}
