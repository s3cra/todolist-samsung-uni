package com.example.module3todo.data.local

import com.example.module3todo.data.model.TodoItemDto

interface ITodoJsonDataSource {
    fun getTodos(): List<TodoItemDto>
}