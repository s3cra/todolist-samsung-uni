package com.example.module3todo.data.local

import android.content.Context
import com.example.module3todo.data.model.TodoItemDto
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class TodoJsonDataSource(private val context: Context) : ITodoJsonDataSource {
    private val gson = Gson()

    override fun getTodos(): List<TodoItemDto> {
        val json = context.assets.open("todos.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<TodoItemDto>>() {}.type
        return gson.fromJson(json, type)
    }
}