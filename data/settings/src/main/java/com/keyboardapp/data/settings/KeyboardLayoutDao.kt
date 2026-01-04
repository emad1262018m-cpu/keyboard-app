package com.keyboardapp.data.settings

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KeyboardLayoutDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLayout(layout: KeyboardLayoutEntity): Long
    
    @Update
    suspend fun updateLayout(layout: KeyboardLayoutEntity)
    
    @Delete
    suspend fun deleteLayout(layout: KeyboardLayoutEntity)
    
    @Query("SELECT * FROM keyboard_layouts WHERE id = :layoutId")
    suspend fun getLayoutById(layoutId: Long): KeyboardLayoutEntity?
    
    @Query("SELECT * FROM keyboard_layouts WHERE language = :language ORDER BY updatedAt DESC")
    fun getLayoutsByLanguage(language: String): Flow<List<KeyboardLayoutEntity>>
    
    @Query("SELECT * FROM keyboard_layouts WHERE isDefault = 1 AND language = :language LIMIT 1")
    suspend fun getDefaultLayout(language: String): KeyboardLayoutEntity?
    
    @Query("DELETE FROM keyboard_layouts WHERE id = :layoutId")
    suspend fun deleteLayoutById(layoutId: Long)
}