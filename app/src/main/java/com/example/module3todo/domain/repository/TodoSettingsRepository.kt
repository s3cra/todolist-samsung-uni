package com.example.module3todo.domain.repository

import kotlinx.coroutines.flow.Flow

interface TodoSettingsRepository {
    fun observeCompletedColorEnabled(): Flow<Boolean>
    suspend fun setCompletedColorEnabled(enabled: Boolean)
}
