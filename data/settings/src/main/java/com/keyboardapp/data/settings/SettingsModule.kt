package com.keyboardapp.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    
    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.filesDir.resolve("keyboard_settings.preferences_pb") }
        )
    }
    
    @Provides
    @Singleton
    fun provideSettingsRepository(dataStore: DataStore<Preferences>): SettingsRepository {
        return SettingsRepository(dataStore)
    }
    
    @Provides
    @Singleton
    fun provideKeyboardDatabase(@ApplicationContext context: Context): KeyboardDatabase {
        return Room.databaseBuilder(
            context,
            KeyboardDatabase::class.java,
            "keyboard_database"
        ).addCallback(DatabaseCallback()).build()
    }
    
    @Provides
    @Singleton
    fun provideKeyboardLayoutDao(db: KeyboardDatabase): KeyboardLayoutDao {
        return db.keyboardLayoutDao()
    }
    
    @Provides
    @Singleton
    fun provideLayoutRepository(layoutDao: KeyboardLayoutDao): LayoutRepository {
        return LayoutRepository(layoutDao)
    }
    
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
}
