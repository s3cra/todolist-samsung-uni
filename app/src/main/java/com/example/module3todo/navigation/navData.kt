package com.example.module3todo.navigation

import kotlinx.serialization.Serializable

@Serializable
object TodoList

@Serializable
object NewTodo

@Serializable
data class EditTodo(val todoId: Int)
