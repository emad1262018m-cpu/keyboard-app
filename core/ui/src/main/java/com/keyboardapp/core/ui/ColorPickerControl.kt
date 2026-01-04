package com.keyboardapp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColorPickerControl(
    label: String,
    currentColor: String,  // Hex format: "#FFFFFF"
    onColorSelected: (String) -> Unit
) {
    val colorPalette = listOf(
        "#000000" to "Black",
        "#FFFFFF" to "White",
        "#FF0000" to "Red",
        "#00FF00" to "Green",
        "#0000FF" to "Blue",
        "#FFFF00" to "Yellow",
        "#F0F0F0" to "Light Gray",
        "#CCCCCC" to "Medium Gray"
    )
    
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            colorPalette.forEach { (hex, name) ->
                val interactionSource = remember { MutableInteractionSource() }
                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { onColorSelected(hex) }
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(android.graphics.Color.parseColor(hex)),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = if (currentColor == hex) 3.dp else 1.dp,
                                color = if (currentColor == hex) Color.Black else Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                    Text(
                        text = name,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
