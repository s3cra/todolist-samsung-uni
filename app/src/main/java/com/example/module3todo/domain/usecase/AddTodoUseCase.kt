package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.repository.TodoRepository

class AddTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(title: String, description: String, isCompleted: Boolean) {
        repository.addTodo(title, description, isCompleted)
    }
}
