package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.repository.TodoRepository

class ObserveTodosUseCase(private val repository: TodoRepository) {
    operator fun invoke() = repository.observeTodos()
}
