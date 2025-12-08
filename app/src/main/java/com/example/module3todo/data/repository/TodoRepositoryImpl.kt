package com.example.module3todo.data.repository

import com.example.module3todo.data.local.TodoJsonDataSource
import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val source: TodoJsonDataSource
) : TodoRepository {


    override suspend fun getTodos(): List<TodoItem> {
        return source.getTodos().map { todoItemDto ->
            TodoItem(todoItemDto.id,
            todoItemDto.title,
            todoItemDto.description,
            todoItemDto.isCompleted) }
    }

    override suspend fun toggleTodo(id: Int) {
        TODO("Not yet implemented")
    }
}