package com.keyboardapp.core.keyboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import com.keyboardapp.core.input.TextComposer

@Composable
fun KeyboardView(
    textComposer: TextComposer?,
    getPreviewText: () -> String,
    onTextChanged: (String) -> Unit,
    fontSize: Int = 32,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color(0xFFF0F0F0),
    borderColor: Color = Color(0xFFCCCCCC),
    isBold: Boolean = false,
    shiftManager: ShiftManager = ShiftManager(),
    onSettingsClick: () -> Unit = {},
    fontFamily: androidx.compose.ui.text.font.FontFamily = androidx.compose.ui.text.font.FontFamily.Default,
    modifier: Modifier = Modifier
) {
    val layout = LayoutManager.getDefaultQwertyLayout()

    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2C2C2C))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            layout.rows.forEach { row ->
                KeyboardRow(
                    keys = row,
                    fontSize = fontSize,
                    textColor = textColor,
                    backgroundColor = backgroundColor,
                    borderColor = borderColor,
                    isBold = isBold,
                    isShiftPressed = shiftManager.isShiftActive,
                    fontFamily = fontFamily,
                    onKeyPressed = { keyData ->
                        Log.d(TAG, "Key pressed: '${keyData.displayChar}' (keyCode: ${keyData.keyCode})")

                        val currentText = getPreviewText()

                        when (keyData.keyCode) {
                            LayoutManager.KEYCODE_SHIFT -> {
                                shiftManager.onShiftTapped()
                            }

                            LayoutManager.KEYCODE_BACKSPACE -> {
                                textComposer?.deleteCharacter()
                                onTextChanged(currentText.dropLast(1))
                            }

                            LayoutManager.KEYCODE_SPACE -> {
                                textComposer?.insertSpace()
                                onTextChanged(currentText + " ")
                            }

                            LayoutManager.KEYCODE_ENTER -> {
                                textComposer?.insertNewLine()
                                onTextChanged(currentText + "\n")
                            }

                            else -> {
                                val displayChar = shiftManager.getDisplayCharacter(keyData.char)
                                textComposer?.insertCharacter(displayChar)
                                onTextChanged(currentText + displayChar)
                                shiftManager.onCharacterTyped()
                            }
                        }
                    }
                )
            }
        }

        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        ) {
            Text("⚙️", fontSize = 20.sp)
        }
    }
}

@Composable
fun KeyboardRow(
    keys: List<KeyData>,
    fontSize: Int,
    textColor: Color,
    backgroundColor: Color,
    borderColor: Color,
    isBold: Boolean,
    isShiftPressed: Boolean,
    fontFamily: androidx.compose.ui.text.font.FontFamily = androidx.compose.ui.text.font.FontFamily.Default,
    onKeyPressed: (KeyData) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        keys.forEach { keyData ->
            KeyCell(
                keyData = keyData,
                fontSize = fontSize,
                textColor = textColor,
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                isBold = isBold,
                isShiftPressed = isShiftPressed,
                fontFamily = fontFamily,
                onClick = onKeyPressed,
                modifier = Modifier.weight(keyData.width)
            )
        }
    }
}

private const val TAG = "KeyboardView"
