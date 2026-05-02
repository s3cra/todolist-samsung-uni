package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.repository.TodoSettingsRepository

class SetCompletedColorUseCase(private val repository: TodoSettingsRepository) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.setCompletedColorEnabled(enabled)
    }
}
