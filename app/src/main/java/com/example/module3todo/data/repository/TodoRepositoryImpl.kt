package com.example.module3todo.data.repository

import com.example.module3todo.data.local.ITodoJsonDataSource
import com.example.module3todo.data.local.TodoDao
import com.example.module3todo.data.local.TodoEntity
import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val todoDao: TodoDao,
    private val jsonDataSource: ITodoJsonDataSource,
) : TodoRepository {

    override fun observeTodos(): Flow<List<TodoItem>> =
        todoDao.observeTodos().map { items ->
            items.map { it.toDomain() }
        }

    override suspend fun importTodosIfNeeded() {
        if (todoDao.getCount() > 0) return

        val imported = jsonDataSource.getTodos().map { dto ->
            TodoEntity(
                id = dto.id,
                title = dto.title,
                description = dto.description,
                isCompleted = dto.isCompleted,
            )
        }
        todoDao.insertAll(imported)
    }

    override suspend fun addTodo(title: String, description: String, isCompleted: Boolean) {
        todoDao.insert(
            TodoEntity(
                title = title,
                description = description,
                isCompleted = isCompleted,
            )
        )
    }

    override suspend fun updateTodo(id: Int, title: String, description: String, isCompleted: Boolean) {
        todoDao.update(
            TodoEntity(
                id = id,
                title = title,
                description = description,
                isCompleted = isCompleted,
            )
        )
    }

    override suspend fun deleteTodo(id: Int) {
        todoDao.getTodoById(id)?.let { todoDao.delete(it) }
    }

    override suspend fun toggleTodo(id: Int) {
        val item = todoDao.getTodoById(id) ?: return
        todoDao.update(item.copy(isCompleted = !item.isCompleted))
    }

    private fun TodoEntity.toDomain(): TodoItem =
        TodoItem(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted,
        )
}
