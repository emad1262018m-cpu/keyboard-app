package com.keyboardapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.keyboardapp.core.input.InputFieldPreview
import com.keyboardapp.core.input.TextComposer
import com.keyboardapp.core.keyboard.KeyboardView
import com.keyboardapp.core.keyboard.ShiftManager

@Composable
fun KeyboardScreen(
    textComposer: TextComposer?,
    keyboardFontSize: Int = 32,
    inputFieldFontSize: Int = 28,
    fontFamily: FontFamily = FontFamily.Default,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color(0xFFF0F0F0),
    borderColor: Color = Color(0xFFCCCCCC),
    isBold: Boolean = false,
    onSettingsClick: () -> Unit = {}
) {
    var displayedText by remember { mutableStateOf("") }
    val shiftManager = remember { ShiftManager() }

    Column(modifier = Modifier.fillMaxWidth()) {
        InputFieldPreview(
            text = displayedText,
            fontSizeSp = inputFieldFontSize,
            textColor = textColor,
            backgroundColor = Color.White,
            isBold = isBold,
            fontFamily = fontFamily
        )

        KeyboardView(
            textComposer = textComposer,
            getPreviewText = { displayedText },
            onTextChanged = { newText ->
                displayedText = newText
            },
            fontSize = keyboardFontSize,
            textColor = textColor,
            backgroundColor = backgroundColor,
            borderColor = borderColor,
            isBold = isBold,
            shiftManager = shiftManager,
            onSettingsClick = onSettingsClick,
            fontFamily = fontFamily
        )
    }
}
