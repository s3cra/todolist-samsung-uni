package com.example.module3todo.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.module3todo.data.local.TodoJsonDataSource
import com.example.module3todo.data.repository.TodoRepositoryImpl
import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.domain.usecase.GetTodosUseCase
import com.example.module3todo.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase) : ViewModel() {
    val items = mutableStateOf(listOf<TodoItem>())

    init {
        loadTodos()
    }


    fun loadTodos() {
        viewModelScope.launch {
            runCatching {
                getTodosUseCase()
            }.onSuccess { todos ->
                items.value = todos
            }.onFailure { error ->
                Log.e("TodoViewModel", "Error loading todos", error)
            }
        }
    }

    fun toggleTodo(id: Int){
        viewModelScope.launch {
            runCatching {
                toggleTodoUseCase(id)
            }.onSuccess { todos ->
                loadTodos()
            }.onFailure { error ->
                Log.e("TodoViewModel", "Error toggling todo", error)
            }
        }
    }


}

class TodoViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewModel(
            GetTodosUseCase(TodoRepositoryImpl(TodoJsonDataSource(context))),
            ToggleTodoUseCase(TodoRepositoryImpl(TodoJsonDataSource(context)))
        ) as T
    }
}
