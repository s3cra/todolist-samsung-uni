package com.example.module3todo

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.module3todo.data.local.TodoJsonDataSource
import com.example.module3todo.data.model.TodoItemDto
import com.example.module3todo.data.repository.TodoRepositoryImpl
import com.example.module3todo.domain.usecase.GetTodosUseCase
import com.example.module3todo.domain.usecase.ToggleTodoUseCase
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.junit.Test

import org.junit.Assert.*




class ExampleUnitTest {
    @Test
    fun getTodosReturns3Tasks(){

        val dataSource = TodoJsonDataSourceTest()
        val repository = TodoRepositoryImpl(dataSource)
        val useCase = GetTodosUseCase(repository)

            val todos = useCase()
            assertEquals(3, todos.size)

    }

    @Test
    fun toggleToggles(){
        val dataSource = TodoJsonDataSourceTest()
        val repository = TodoRepositoryImpl(dataSource)
        val useCase = GetTodosUseCase(repository)
        val useCase2 = ToggleTodoUseCase(repository)

        val todos = useCase()
        assertEquals(false, todos[0].isCompleted)
        useCase2(todos[0].id)
        assertEquals(true, todos[0].isCompleted )
    }
}