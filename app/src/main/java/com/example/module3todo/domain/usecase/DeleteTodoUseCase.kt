package com.example.module3todo.domain.usecase

import com.example.module3todo.domain.repository.TodoRepository

class DeleteTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) {
        repository.deleteTodo(id)
    }
}
