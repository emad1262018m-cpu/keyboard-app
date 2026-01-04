package com.keyboardapp.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FontSizeControl(
    fontSize: Int,
    onFontSizeChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("Font Size: $fontSize sp")
        Slider(
            value = fontSize.toFloat(),
            onValueChange = { onFontSizeChange(it.toInt()) },
            valueRange = 16f..64f,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
    }
}
