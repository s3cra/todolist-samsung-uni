package com.example.module3todo.domain.repository

import com.example.module3todo.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}
