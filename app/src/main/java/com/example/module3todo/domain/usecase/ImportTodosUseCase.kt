package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.repository.TodoRepository

class ImportTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke() {
        repository.importTodosIfNeeded()
    }
}
