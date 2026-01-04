package com.keyboardapp.core.keyboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    modifier: Modifier = Modifier,
    keyboardHeightDp: Float = 250f,
    onHeightChanged: (Float) -> Unit = {},
    layoutEditor: LayoutEditor? = null
) {
    var currentHeight by remember { mutableStateOf(keyboardHeightDp) }
    val layout = layoutEditor?.currentLayout ?: LayoutManager.getDefaultQwertyLayout()
    val isEditMode = layoutEditor?.isEditMode ?: false
    val interactionSource = remember { MutableInteractionSource() }

    // Handle height changes
    LaunchedEffect(currentHeight) {
        onHeightChanged(currentHeight)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(currentHeight.dp)
            .background(backgroundColor)
            .border(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            layout.rows.forEachIndexed { rowIndex, row ->
                KeyboardRow(
                    keys = row,
                    fontSize = fontSize,
                    textColor = textColor,
                    backgroundColor = backgroundColor,
                    borderColor = borderColor,
                    isBold = isBold,
                    isShiftPressed = shiftManager.isShiftActive,
                    fontFamily = fontFamily,
                    isEditMode = isEditMode,
                    rowIndex = rowIndex,
                    onKeyPressed = { keyData ->
                        if (isEditMode) return@KeyboardRow
                        
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
                    },
                    onDragStart = { char ->
                        layoutEditor?.setDraggedKey(char)
                    },
                    onDragEnd = {
                        layoutEditor?.setDraggedKey(null)
                    },
                    onDrop = { targetRow, targetCol ->
                        layoutEditor?.draggedKeyChar?.let { draggedChar ->
                            // Find the position of the dragged key
                            var fromRow = -1
                            var fromCol = -1
                            layout.rows.forEachIndexed { rIndex, row ->
                                row.forEachIndexed { cIndex, key ->
                                    if (key.char == draggedChar) {
                                        fromRow = rIndex
                                        fromCol = cIndex
                                    }
                                }
                            }
                            
                            if (fromRow >= 0 && fromCol >= 0) {
                                layoutEditor.moveKey(draggedChar, fromRow, fromCol, targetRow, targetCol)
                            }
                        }
                    }
                )
            }
        }

        // Settings button (top-right)
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp),
            interactionSource = interactionSource
        ) {
            Text("⚙️", fontSize = 20.sp)
        }

        // Resize handle (bottom-right corner)
        if (!isEditMode) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(40.dp)
                    .background(Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(topStart = 8.dp))
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            val density = this
                            val newHeight = (currentHeight + dragAmount.y / density.density).coerceIn(150f, 400f)
                            currentHeight = newHeight
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⤢",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        // Edit mode overlay
        if (isEditMode) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Edit Mode - Drag keys to rearrange",
                    color = Color.Blue,
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }
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
    isEditMode: Boolean = false,
    rowIndex: Int = 0,
    onKeyPressed: (KeyData) -> Unit,
    onDragStart: (String) -> Unit = {},
    onDragEnd: () -> Unit = {},
    onDrop: (Int, Int) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        keys.forEachIndexed { colIndex, keyData ->
            KeyCell(
                keyData = keyData,
                fontSize = fontSize,
                textColor = textColor,
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                isBold = isBold,
                isShiftPressed = isShiftPressed,
                fontFamily = fontFamily,
                isEditMode = isEditMode,
                rowIndex = rowIndex,
                colIndex = colIndex,
                onDragStart = { onDragStart(keyData.char) },
                onDragEnd = onDragEnd,
                onDrop = { targetRow, targetCol ->
                    onDrop(targetRow, targetCol)
                },
                onClick = onKeyPressed,
                modifier = Modifier.weight(keyData.width)
            )
        }
    }
}

private const val TAG = "KeyboardView"
