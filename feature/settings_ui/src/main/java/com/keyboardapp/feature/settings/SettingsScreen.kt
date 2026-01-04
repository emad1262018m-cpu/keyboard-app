package com.keyboardapp.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keyboardapp.core.ui.*
import com.keyboardapp.data.settings.KeyboardSettings
import com.keyboardapp.data.settings.LayoutRepository
import com.keyboardapp.core.keyboard.LayoutEditor
import com.keyboardapp.core.keyboard.LayoutManager
import com.keyboardapp.core.keyboard.KeyboardLayout
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    layoutRepository: LayoutRepository? = null,
    layoutEditor: LayoutEditor? = null
) {
    val settings by viewModel.settingsFlow.collectAsState()
    var showLayoutManager by remember { mutableStateOf(false) }
    var showHeightControl by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    
    if (showLayoutManager && layoutRepository != null) {
        LayoutManagerScreen(
            layoutRepository = layoutRepository,
            currentLanguage = settings.language,
            onLoadLayout = { layoutId ->
                scope.launch {
                    val layout = layoutRepository.loadLayout(layoutId)
                    if (layout != null && layoutEditor != null) {
                        val keyboardLayout = LayoutManager.loadLayout(layout)
                        layoutEditor.currentLayout = keyboardLayout
                        LayoutManager.setCurrentLayoutId(layoutId)
                    }
                }
                showLayoutManager = false
            },
            onEditMode = {
                layoutEditor?.enterEditMode()
                showLayoutManager = false
            },
            modifier = modifier
        )
    } else {
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { showLayoutManager = true },
                        modifier = Modifier.weight(1f),
                        enabled = layoutRepository != null
                    ) {
                        Text("Layout Manager")
                    }
                    Button(
                        onClick = { showHeightControl = !showHeightControl },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Keyboard Height")
                    }
                }
            }
            
            if (showHeightControl) {
                item {
                    Column {
                        Text(
                            text = "Keyboard Height: ${settings.keyboardHeightDp.toInt()}dp",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        androidx.compose.material3.Slider(
                            value = settings.keyboardHeightDp,
                            onValueChange = { value ->
                                viewModel.updateKeyboardHeight(value)
                            },
                            valueRange = 150f..400f,
                            modifier = Modifier.fillMaxWidth(),
                            interactionSource = interactionSource
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("150dp", fontSize = 12.sp, color = Color.Gray)
                            Text("400dp", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
            }
            
            item {
                HorizontalDivider()
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
