package com.example.module3todo.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.module3todo.domain.repository.TodoSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.todoSettingsDataStore by preferencesDataStore(name = "todo_settings")

class TodoSettingsDataStore(private val context: Context) : TodoSettingsRepository {
    private val completedColorKey = booleanPreferencesKey("completed_color_enabled")

    override fun observeCompletedColorEnabled(): Flow<Boolean> =
        context.todoSettingsDataStore.data.map { prefs ->
            prefs[completedColorKey] ?: false
        }

    override suspend fun setCompletedColorEnabled(enabled: Boolean) {
        context.todoSettingsDataStore.edit { prefs ->
            prefs[completedColorKey] = enabled
        }
    }
}
