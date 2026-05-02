package com.example.module3todo.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.module3todo.data.local.TodoDatabase
import com.example.module3todo.data.local.TodoJsonDataSource
import com.example.module3todo.data.preferences.TodoSettingsDataStore
import com.example.module3todo.data.repository.TodoRepositoryImpl
import com.example.module3todo.domain.model.TodoItem
import com.example.module3todo.domain.usecase.AddTodoUseCase
import com.example.module3todo.domain.usecase.DeleteTodoUseCase
import com.example.module3todo.domain.usecase.ImportTodosUseCase
import com.example.module3todo.domain.usecase.ObserveCompletedColorUseCase
import com.example.module3todo.domain.usecase.ObserveTodosUseCase
import com.example.module3todo.domain.usecase.SetCompletedColorUseCase
import com.example.module3todo.domain.usecase.ToggleTodoUseCase
import com.example.module3todo.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val observeTodosUseCase: ObserveTodosUseCase,
    private val importTodosUseCase: ImportTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val observeCompletedColorUseCase: ObserveCompletedColorUseCase,
    private val setCompletedColorUseCase: SetCompletedColorUseCase,
) : ViewModel() {
    private val _items = MutableStateFlow<List<TodoItem>>(emptyList())
    val items: StateFlow<List<TodoItem>> = _items.asStateFlow()

    private val _completedColorEnabled = MutableStateFlow(false)
    val completedColorEnabled: StateFlow<Boolean> = _completedColorEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            importTodosUseCase()
        }
        viewModelScope.launch {
            observeTodosUseCase().collect { _items.value = it }
        }
        viewModelScope.launch {
            observeCompletedColorUseCase().collect { _completedColorEnabled.value = it }
        }
    }

    fun findTodo(id: Int?): TodoItem? = items.value.find { it.id == id }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
        }
    }

    fun saveTodo(id: Int?, title: String, description: String, isCompleted: Boolean) {
        viewModelScope.launch {
            if (id == null) {
                addTodoUseCase(title.trim(), description.trim(), isCompleted)
            } else {
                updateTodoUseCase(id, title.trim(), description.trim(), isCompleted)
            }
        }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch {
            deleteTodoUseCase(id)
        }
    }

    fun setCompletedColorEnabled(enabled: Boolean) {
        viewModelScope.launch {
            setCompletedColorUseCase(enabled)
        }
    }
}

class TodoViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val todoDao = TodoDatabase.getInstance(context).todoDao()
        val repository = TodoRepositoryImpl(todoDao, TodoJsonDataSource(context))
        val settingsRepository = TodoSettingsDataStore(context)
        return TodoViewModel(
            observeTodosUseCase = ObserveTodosUseCase(repository),
            importTodosUseCase = ImportTodosUseCase(repository),
            addTodoUseCase = AddTodoUseCase(repository),
            updateTodoUseCase = UpdateTodoUseCase(repository),
            deleteTodoUseCase = DeleteTodoUseCase(repository),
            toggleTodoUseCase = ToggleTodoUseCase(repository),
            observeCompletedColorUseCase = ObserveCompletedColorUseCase(settingsRepository),
            setCompletedColorUseCase = SetCompletedColorUseCase(settingsRepository),
        ) as T
    }
}
