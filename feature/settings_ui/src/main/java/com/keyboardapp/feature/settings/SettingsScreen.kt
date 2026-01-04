package com.keyboardapp.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keyboardapp.core.ui.*
import com.keyboardapp.data.settings.KeyboardSettings

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settings by viewModel.settingsFlow.collectAsState()
    
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Keyboard Settings",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        
        item {
            FontSizeControl(
                label = "Keyboard Font Size",
                currentSize = settings.keyboardFontSizeSp,
                onSizeChanged = { viewModel.updateKeyboardFontSize(it) }
            )
        }
        
        item {
            FontSizeControl(
                label = "Input Field Font Size",
                currentSize = settings.inputFieldFontSizeSp,
                maxSize = 56,
                onSizeChanged = { viewModel.updateInputFieldFontSize(it) }
            )
        }
        
        item {
            FontFamilyControl(
                label = "Font Family",
                currentFont = settings.fontFamily,
                onFontSelected = { viewModel.updateFontFamily(it) }
            )
        }
        
        item {
            BoldToggleControl(
                label = "Bold Characters",
                isEnabled = settings.isBold,
                onToggle = { viewModel.updateBold(it) }
            )
        }
        
        item {
            Text(
                text = "Colors",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        item {
            ColorPickerControl(
                label = "Text Color",
                currentColor = settings.textColor,
                onColorSelected = { viewModel.updateTextColor(it) }
            )
        }
        
        item {
            ColorPickerControl(
                label = "Background Color",
                currentColor = settings.backgroundColor,
                onColorSelected = { viewModel.updateBackgroundColor(it) }
            )
        }
        
        item {
            ColorPickerControl(
                label = "Border Color",
                currentColor = settings.borderColor,
                onColorSelected = { viewModel.updateBorderColor(it) }
            )
        }
        
        item {
            LanguageSwitchControl(
                label = "Language",
                currentLanguage = settings.language,
                onLanguageSelected = { viewModel.updateLanguage(it) }
            )
        }
        
        item {
            Text(
                text = "Preview",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        item {
            SettingsPreview(settings = settings)
        }
    }
}

@Composable
fun SettingsPreview(settings: KeyboardSettings) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(android.graphics.Color.parseColor(settings.backgroundColor)),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Color(android.graphics.Color.parseColor(settings.borderColor)),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sample Text",
            fontSize = settings.keyboardFontSizeSp.sp,
            fontWeight = if (settings.isBold) FontWeight.Bold else FontWeight.Normal,
            color = Color(android.graphics.Color.parseColor(settings.textColor))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Preview of selected settings",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}
