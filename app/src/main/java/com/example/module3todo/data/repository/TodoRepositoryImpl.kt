package com.example.module3todo.data.repository

import android.util.Log
import com.example.module3todo.data.local.ITodoJsonDataSource
import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val source: ITodoJsonDataSource
) : TodoRepository {

    private var cache: MutableList<TodoItem> = mutableListOf()

    override fun getTodos(): List<TodoItem> {
        if (cache.isEmpty()) {
            cache = source.getTodos().map { todoItemDto ->
                TodoItem(
                    todoItemDto.id,
                    todoItemDto.title,
                    todoItemDto.description,
                    todoItemDto.isCompleted
                )
            }.toMutableList()
        }
        return cache
    }

    override fun toggleTodo(id: Int) {
        val i = cache.indexOfFirst { it.id == id }

        cache[i].isCompleted = !cache[i].isCompleted
    }
}