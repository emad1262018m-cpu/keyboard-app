package com.keyboardapp.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FontFamilyControl(
    label: String,
    currentFont: String,
    onFontSelected: (String) -> Unit
) {
    val fontFamilies = listOf("Roboto", "Arial", "Noto Sans Arabic", "Courier New")
    
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        var expanded by remember { mutableStateOf(false) }
        
        Box {
            Button(
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("$currentFont ▼", fontSize = 16.sp)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                fontFamilies.forEach { font ->
                    DropdownMenuItem(
                        text = { Text(font, fontSize = 16.sp) },
                        onClick = {
                            onFontSelected(font)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
