package com.example.module3todo

import android.content.Context
import com.example.module3todo.data.local.ITodoJsonDataSource
import com.example.module3todo.data.model.TodoItemDto
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.File

class TodoJsonDataSourceTest() : ITodoJsonDataSource {
    private val gson = Gson()

    override fun getTodos(): List<TodoItemDto> {
        val file = javaClass.classLoader?.getResourceAsStream("todos.json")
        val json = file?.bufferedReader().use { it?.readText() }
        val type = object : TypeToken<List<TodoItemDto>>() {}.type
        return gson.fromJson(json, type)
    }
}