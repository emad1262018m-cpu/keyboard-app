package com.keyboardapp.core.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyCell(
    keyData: KeyData,
    fontSize: Int = 32,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color(0xFFF0F0F0),
    borderColor: Color = Color(0xFFCCCCCC),
    isBold: Boolean = false,
    isShiftPressed: Boolean = false,
    fontFamily: FontFamily = FontFamily.Default,
    isEditMode: Boolean = false,
    rowIndex: Int = 0,
    colIndex: Int = 0,
    onDragStart: () -> Unit = {},
    onDragEnd: () -> Unit = {},
    onDrop: (Int, Int) -> Unit = { _, _ -> },
    onClick: (KeyData) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var isDragging by remember { mutableStateOf(false) }
    
    val currentBackgroundColor = when {
        keyData.char == "⇧" && isShiftPressed -> Color.Yellow.copy(alpha = 0.7f)
        isDragging -> Color.Cyan.copy(alpha = 0.5f)
        isPressed -> backgroundColor.copy(alpha = 0.7f)
        else -> backgroundColor
    }
    
    val displayText = when {
        keyData.keyCode >= 0 && keyData.char.length == 1 && keyData.char[0].isLetter() -> {
            if (isShiftPressed) keyData.char.uppercase() else keyData.char.lowercase()
        }
        else -> keyData.displayChar
    }
    
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(currentBackgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .then(
                if (isEditMode) {
                    Modifier.pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                isDragging = true
                                onDragStart()
                            },
                            onDragEnd = {
                                isDragging = false
                                onDragEnd()
                            },
                            onDrag = { change, _ ->
                                change.consume()
                            }
                        )
                    }
                } else {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onClick(keyData)
                    }
                }
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = displayText,
            fontSize = fontSize.sp,
            color = textColor,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            fontFamily = fontFamily
        )
    }
}
