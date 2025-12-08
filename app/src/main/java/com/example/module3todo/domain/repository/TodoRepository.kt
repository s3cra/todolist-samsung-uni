package com.example.module3todo.domain.repository

import com.example.module3todo.domain.model.TodoItem

interface TodoRepository {
    fun getTodos(): List<TodoItem>
    fun toggleTodo(id: Int)
}
