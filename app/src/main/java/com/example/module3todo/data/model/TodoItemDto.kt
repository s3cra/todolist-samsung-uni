package com.example.module3todo.data.model

data class TodoItemDto(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean
)
