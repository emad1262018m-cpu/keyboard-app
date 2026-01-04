package com.keyboardapp.core.keyboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LayoutEditor(private val layoutManager: LayoutManager) {
    var isEditMode by mutableStateOf(false)
    var currentLayout by mutableStateOf(layoutManager.getDefaultQwertyLayout())
    var draggedKeyChar by mutableStateOf<String?>(null)
    
    fun enterEditMode() {
        isEditMode = true
        currentLayout = layoutManager.getDefaultQwertyLayout()
    }
    
    fun exitEditMode() {
        isEditMode = false
        draggedKeyChar = null
    }
    
    fun moveKey(char: String, fromRow: Int, fromCol: Int, toRow: Int, toCol: Int) {
        if (fromRow !in currentLayout.rows.indices || fromCol !in currentLayout.rows[fromRow].indices) return
        if (toRow !in currentLayout.rows.indices || toCol !in currentLayout.rows[toRow].indices) return
        
        // Create a mutable copy of the layout
        val tempLayout = currentLayout.rows.map { it.toMutableList() }.toMutableList()
        
        // Get the source and target keys
        val sourceKey = tempLayout[fromRow][fromCol]
        val targetKey = tempLayout[toRow][toCol]
        
        // Swap the keys
        tempLayout[fromRow][fromCol] = targetKey
        tempLayout[toRow][toCol] = sourceKey
        
        currentLayout = KeyboardLayout(tempLayout.map { it.toList() })
    }
    
    fun resetLayout() {
        currentLayout = layoutManager.getDefaultQwertyLayout()
    }
    
    fun getCurrentLayout(): List<List<KeyPosition>> {
        return LayoutManager.convertLayoutToPositions(currentLayout)
    }
    
    fun setDraggedKey(char: String?) {
        draggedKeyChar = char
    }
}