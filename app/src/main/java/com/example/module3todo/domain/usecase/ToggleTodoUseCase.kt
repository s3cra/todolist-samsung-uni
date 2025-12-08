package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    operator fun invoke(id: Int) = repository.toggleTodo(id)
}