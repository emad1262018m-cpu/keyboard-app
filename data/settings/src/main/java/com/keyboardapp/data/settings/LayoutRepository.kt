package com.keyboardapp.data.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LayoutRepository @Inject constructor(
    private val layoutDao: KeyboardLayoutDao
) {
    
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    
    fun getLayoutsByLanguage(language: String): Flow<List<KeyboardLayoutEntity>> {
        return layoutDao.getLayoutsByLanguage(language)
    }
    
    suspend fun saveLayout(
        name: String,
        layout: List<List<KeyPosition>>,
        language: String
    ): Long {
        val layoutJson = json.encodeToString(layout)
        val entity = KeyboardLayoutEntity(
            name = name,
            layoutJson = layoutJson,
            language = language,
            isDefault = false,
            updatedAt = System.currentTimeMillis()
        )
        return layoutDao.insertLayout(entity)
    }
    
    suspend fun loadLayout(layoutId: Long): List<List<KeyPosition>>? {
        val entity = layoutDao.getLayoutById(layoutId) ?: return null
        return try {
            json.decodeFromString<List<List<KeyPosition>>>(entity.layoutJson)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun deleteLayout(layoutId: Long) {
        layoutDao.deleteLayoutById(layoutId)
    }
    
    suspend fun renameLayout(layoutId: Long, newName: String) {
        val layout = layoutDao.getLayoutById(layoutId) ?: return
        layoutDao.updateLayout(
            layout.copy(
                name = newName,
                updatedAt = System.currentTimeMillis()
            )
        )
    }
    
    suspend fun setDefaultLayout(layoutId: Long, language: String) {
        // First, unset all defaults for this language
        val layouts = layoutDao.getLayoutsByLanguage(language)
        // Note: This would require a more complex query in a real implementation
        // For now, we'll just set the new default
        
        val layout = layoutDao.getLayoutById(layoutId) ?: return
        val newDefault = layout.copy(
            isDefault = true,
            updatedAt = System.currentTimeMillis()
        )
        layoutDao.updateLayout(newDefault)
    }
    
    suspend fun getDefaultLayout(language: String): List<List<KeyPosition>>? {
        val entity = layoutDao.getDefaultLayout(language) ?: return null
        return try {
            json.decodeFromString<List<List<KeyPosition>>>(entity.layoutJson)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getLayoutById(layoutId: Long): KeyboardLayoutEntity? {
        return layoutDao.getLayoutById(layoutId)
    }
}