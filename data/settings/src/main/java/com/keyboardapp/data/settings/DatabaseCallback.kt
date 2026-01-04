package com.keyboardapp.data.settings

import androidx.room.DatabaseCallback
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseCallback : DatabaseCallback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        
        // Initialize with default layouts
        val defaultQwerty = LayoutInitializer.getDefaultQwertyLayout()
        val defaultArabic = LayoutInitializer.getDefaultArabicLayout()
        
        val timestamp = System.currentTimeMillis()
        
        // Insert default QWERTY layout
        db.execSQL("""
            INSERT INTO keyboard_layouts (name, layout_json, language, is_default, created_at, updated_at)
            VALUES ('Default QWERTY', '$defaultQwerty', 'en', 1, $timestamp, $timestamp)
        """)
        
        // Insert default Arabic layout
        db.execSQL("""
            INSERT INTO keyboard_layouts (name, layout_json, language, is_default, created_at, updated_at)
            VALUES ('Default Arabic', '$defaultArabic', 'ar', 1, $timestamp, $timestamp)
        """)
    }
}