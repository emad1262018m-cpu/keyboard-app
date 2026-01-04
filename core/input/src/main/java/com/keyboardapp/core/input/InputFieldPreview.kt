package com.keyboardapp.core.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputFieldPreview(
    text: String,
    fontSizeSp: Int,
    textColor: Color,
    backgroundColor: Color,
    isBold: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .border(1.dp, Color.Gray)
            .padding(12.dp)
            .heightIn(min = 60.dp)
    ) {
        Text(
            text = text.ifEmpty { "Start typing..." },
            fontSize = fontSizeSp.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = textColor,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
