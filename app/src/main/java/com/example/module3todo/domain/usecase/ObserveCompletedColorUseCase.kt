package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.repository.TodoSettingsRepository

class ObserveCompletedColorUseCase(private val repository: TodoSettingsRepository) {
    operator fun invoke() = repository.observeCompletedColorEnabled()
}
