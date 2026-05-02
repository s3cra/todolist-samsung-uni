package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.repository.TodoRepository

class UpdateTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int, title: String, description: String, isCompleted: Boolean) {
        repository.updateTodo(id, title, description, isCompleted)
    }
}
