package com.example.module3todo.domain.repository

import com.example.module3todo.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun observeTodos(): Flow<List<TodoItem>>
    suspend fun importTodosIfNeeded()
    suspend fun addTodo(title: String, description: String, isCompleted: Boolean)
    suspend fun updateTodo(id: Int, title: String, description: String, isCompleted: Boolean)
    suspend fun deleteTodo(id: Int)
    suspend fun toggleTodo(id: Int)
}
