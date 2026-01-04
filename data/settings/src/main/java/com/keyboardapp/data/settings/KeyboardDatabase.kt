package com.keyboardapp.data.settings

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [KeyboardLayoutEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KeyboardDatabase : RoomDatabase() {
    abstract fun keyboardLayoutDao(): KeyboardLayoutDao
    
    companion object {
        @Volatile
        private var INSTANCE: KeyboardDatabase? = null
        
        fun getDatabase(context: Context): KeyboardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KeyboardDatabase::class.java,
                    "keyboard_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}