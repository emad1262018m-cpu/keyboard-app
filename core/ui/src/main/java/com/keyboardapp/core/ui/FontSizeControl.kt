package com.keyboardapp.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FontSizeControl(
    label: String,
    currentSize: Int,
    minSize: Int = 16,
    maxSize: Int = 64,
    onSizeChanged: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Slider(
            value = currentSize.toFloat(),
            onValueChange = { onSizeChanged(it.toInt()) },
            valueRange = minSize.toFloat()..maxSize.toFloat(),
            steps = (maxSize - minSize) / 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            thumb = {
                SliderDefaults.Thumb(
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        Text(
            text = "$currentSize sp",
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
