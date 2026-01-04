package com.keyboardapp.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.keyboardapp.data.settings.KeyboardLayoutEntity
import com.keyboardapp.data.settings.LayoutRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LayoutManagerScreen(
    layoutRepository: LayoutRepository,
    currentLanguage: String,
    onLoadLayout: (Long) -> Unit,
    onEditMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    val savedLayouts by layoutRepository.getLayoutsByLanguage(currentLanguage).collectAsState(initial = emptyList())
    var showSaveDialog by remember { mutableStateOf(false) }
    var showEditMode by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Keyboard Layouts",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { showEditMode = true },
                modifier = Modifier.weight(1f)
            ) {
                Text("Edit Layout")
            }
            Button(
                onClick = { showSaveDialog = true },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save Current")
            }
        }
        
        if (showEditMode) {
            Text(
                text = "Long-press keys to drag and reorder them.",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(
                onClick = {
                    showEditMode = false
                    onEditMode()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enter Edit Mode")
            }
        }
        
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        
        Text(
            text = "Saved Layouts (${savedLayouts.size})",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        if (savedLayouts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No saved layouts yet",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn {
                items(savedLayouts) { layout ->
                    LayoutItem(
                        layout = layout,
                        onLoad = {
                            onLoadLayout(layout.id)
                        },
                        onDelete = {
                            scope.launch {
                                layoutRepository.deleteLayout(layout.id)
                            }
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
    
    if (showSaveDialog) {
        SaveLayoutDialog(
            onSave = { name ->
                showSaveDialog = false
                // Save current layout with name - this would be handled by the parent
                // We need to pass a callback for this
            },
            onDismiss = { showSaveDialog = false }
        )
    }
}

@Composable
fun LayoutItem(
    layout: KeyboardLayoutEntity,
    onLoad: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.3f))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = layout.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                if (layout.isDefault) {
                    Text(
                        text = " (Default)",
                        fontSize = 12.sp,
                        color = Color.Blue,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            Text(
                text = "Updated: ${SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).format(Date(layout.updatedAt))}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onLoad, modifier = Modifier.height(40.dp)) {
                Text("Load")
            }
            Button(onClick = onDelete, modifier = Modifier.height(40.dp)) {
                Text("Delete")
            }
        }
    }
}