package com.keyboardapp.core.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguageSwitchControl(
    label: String,
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    val interactionSourceEn = remember { MutableInteractionSource() }
    val interactionSourceAr = remember { MutableInteractionSource() }
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
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { onLanguageSelected("en") },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentLanguage == "en") Color.Blue else Color.LightGray
                ),
                interactionSource = interactionSourceEn
            ) {
                Text("English", fontSize = 14.sp)
            }
            Button(
                onClick = { onLanguageSelected("ar") },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentLanguage == "ar") Color.Blue else Color.LightGray
                ),
                interactionSource = interactionSourceAr
            ) {
                Text("العربية", fontSize = 14.sp)
            }
        }
    }
}
