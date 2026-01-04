package com.keyboardapp.core.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardView(
    onKeyClick: (KeyData) -> Unit,
    isShiftPressed: Boolean = false,
    fontSize: Int = 32,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color(0xFFF0F0F0),
    borderColor: Color = Color(0xFFCCCCCC),
    isBold: Boolean = false,
    modifier: Modifier = Modifier
) {
    val layout = LayoutManager.getDefaultQwertyLayout()
    
    Column(
        modifier = modifier
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
                isShiftPressed = isShiftPressed,
                onKeyClick = onKeyClick
            )
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
    onKeyClick: (KeyData) -> Unit,
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
                onClick = onKeyClick,
                modifier = Modifier.weight(keyData.width)
            )
        }
    }
}
