package com.example.module3todo

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.module3todo.data.local.TodoJsonDataSource
import com.example.module3todo.data.model.TodoItemDto
import com.example.module3todo.data.repository.TodoRepositoryImpl
import com.example.module3todo.domain.usecase.GetTodosUseCase
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.junit.Test

import org.junit.Assert.*




class ExampleUnitTest {
    @Test
    suspend fun getTodosReturns3Tasks(){

        val dataSource = TodoJsonDataSource()
        val repository = TodoRepositoryImpl(dataSource)
        val useCase = GetTodosUseCase(repository)

        coroutineScope {
            val todos = async {
                useCase.invoke()
            }
            assertEquals(3, todos.await().size)
        }
    }
}